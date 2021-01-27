-- 系统配置
-- 菜单
INSERT INTO PM_MENU(MENU_ID, MENU_CODE, MENU_NAME, MENU_ICON, SORT_NUMBER, MENU_TYPE, PARENT_ID, PAGE_ROUTE)
VALUES (1, 'SYSTEM', '系统管理', 'el-icon-s-tools', 100, 'DIRECTORY', 0, NULL);
INSERT INTO PM_MENU(MENU_ID, MENU_CODE, MENU_NAME, MENU_ICON, SORT_NUMBER, MENU_TYPE, PARENT_ID, PAGE_ROUTE)
VALUES (2, 'SYSTEM.USER', '用户管理', 'el-icon-user-solid', 110, 'PAGE', 1, '/workspace/system/user');
INSERT INTO PM_MENU(MENU_ID, MENU_CODE, MENU_NAME, MENU_ICON, SORT_NUMBER, MENU_TYPE, PARENT_ID, PAGE_ROUTE)
VALUES (3, 'SYSTEM.MENU', '菜单管理', 'el-icon-menu', 120, 'PAGE', 1, '/workspace/system/menu');
INSERT INTO PM_MENU(MENU_ID, MENU_CODE, MENU_NAME, MENU_ICON, SORT_NUMBER, MENU_TYPE, PARENT_ID, PAGE_ROUTE)
VALUES (4, 'SYSTEM.ROLE', '角色管理', 'el-icon-s-custom', 130, 'PAGE', 1, '/workspace/system/role');
-- 角色
INSERT INTO PM_ROLE(ROLE_ID, ROLE_CODE, ROLE_NAME, PARENT_ID, IS_ADMIN)
VALUES (1, 'ADMINISTRATOR', '管理员', 0, 1);
