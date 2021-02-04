package pers.dog.project.manager.component.argument;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 分页参数解析组件
 *
 * @author 废柴 2021/2/3 19:53
 */
public class PageHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String DEFAULT_PAGE_PARAMETER = "page";
    private static final String DEFAULT_SIZE_PARAMETER = "size";
    private static final String DEFAULT_SORT_PARAMETER = "sort";
    private static final int DEFAULT_MAX_PAGE_SIZE = 2000;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Page.class.equals(parameter.getParameterType());
    }

    @Override
    public Page<?> resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer mavContainer,
                                   NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {
        assertPageableUniqueness(methodParameter);
        String pageString = webRequest.getParameter(DEFAULT_PAGE_PARAMETER);
        String pageSizeString = webRequest.getParameter(DEFAULT_SIZE_PARAMETER);
        Optional<Integer> page = parseAndApplyBoundaries(pageString, Integer.MAX_VALUE, true);
        Optional<Integer> pageSize = parseAndApplyBoundaries(pageSizeString, DEFAULT_MAX_PAGE_SIZE, false);
        if (!(page.isPresent() && pageSize.isPresent())) {
            return new Page<>(0, 10);
        }
        int p = page.orElseThrow(IllegalStateException::new);
        int ps = Math.max(0, pageSize.orElseThrow(IllegalStateException::new));
        Page<?> value = new Page<>(p, ps);
        value.addOrder(resolveArgumentSort(webRequest));
        return value;
    }

    private List<OrderItem> resolveArgumentSort(NativeWebRequest webRequest) {
        List<OrderItem> orderItemList = new ArrayList<>();
        String[] sort = webRequest.getParameterValues(DEFAULT_SORT_PARAMETER);
        if (sort != null && sort.length > 0) {
            for (String s : sort) {
                if (s.contains(",")) {
                    String[] order = s.split(",");
                    if (StringUtils.hasText(order[1]) && order[1].equalsIgnoreCase("DESC")) {
                        orderItemList.add(OrderItem.desc(order[0]));
                    } else {
                        orderItemList.add(OrderItem.asc(order[0]));
                    }
                } else {
                    orderItemList.add(OrderItem.asc(s));
                }
            }
        }
        return orderItemList;
    }

    private static void assertPageableUniqueness(MethodParameter parameter) {
        Method method = parameter.getMethod();
        if (method == null) {
            throw new IllegalArgumentException(String.format("Method parameter %s is not backed by a method.", parameter));
        }
        if (containsMoreThanOnePageableParameter(method)) {
            throw new IllegalArgumentException(String.format("Method %s has more than one Page parameter.", method));
        }
    }

    private static boolean containsMoreThanOnePageableParameter(Method method) {
        boolean pageableFound = false;
        for (Class<?> type : method.getParameterTypes()) {
            if (pageableFound && type.equals(Page.class)) {
                return true;
            }
            if (type.equals(Page.class)) {
                pageableFound = true;
            }
        }
        return false;
    }

    private Optional<Integer> parseAndApplyBoundaries(@Nullable String parameter, int upper, boolean shiftIndex) {
        if (!StringUtils.hasText(parameter)) {
            return Optional.empty();
        }
        try {
            int parsed = Integer.parseInt(parameter) - (shiftIndex ? 1 : 0);
            return Optional.of(parsed < 0 ? 0 : Math.min(parsed, upper));
        } catch (NumberFormatException e) {
            return Optional.of(0);
        }
    }
}
