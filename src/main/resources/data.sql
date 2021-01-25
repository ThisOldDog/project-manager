-- 系统配置
-- 菜单
insert into pm_menu(menu_id, menu_code, menu_name, menu_icon, sort_number, menu_type, parent_id, page_route)
values (1, 'SYSTEM', '系统管理', 'el-icon-s-tools', 100, 'DIRECTORY', 0, null);
insert into pm_menu(menu_id, menu_code, menu_name, menu_icon, sort_number, menu_type, parent_id, page_route)
values (2, 'SYSTEM.USER', '用户管理', 'el-icon-user-solid', 110, 'PAGE', 1, '/workspace/system/user');
insert into pm_menu(menu_id, menu_code, menu_name, menu_icon, sort_number, menu_type, parent_id, page_route)
values (3, 'SYSTEM.MENU', '菜单管理', 'el-icon-menu', 120, 'PAGE', 1, '/workspace/system/menu');
insert into pm_menu(menu_id, menu_code, menu_name, menu_icon, sort_number, menu_type, parent_id, page_route)
values (4, 'SYSTEM.ROLE', '角色管理', 'el-icon-s-custom', 130, 'PAGE', 1, '/workspace/system/role');
-- 角色
insert into pm_role(role_id, role_code, role_name, parent_id, is_administrator)
values (1, 'ADMINISTRATOR', '管理员', 0, true)
