insert into pm_menu(menu_id, menu_code, menu_name, menu_icon, sort_number, menu_type, parent_id, page_route)
values (1, 'SYSTEM_MANAGEMENT', '系统管理', 'el-icon-s-tools', 100, 'DIRECTORY', 0, null);
insert into pm_menu(menu_id, menu_code, menu_name, menu_icon, sort_number, menu_type, parent_id, page_route)
values (2, 'USER_MANAGEMENT', '用户管理', 'el-icon-user-solid', 110, 'PAGE', 1, 'system/user');
-- (3, 'MENU_MANAGEMENT', '菜单管理', 120, 'PAGE', null)
-- (4, 'ROLE_MANAGEMENT', '角色管理', 130, 'PAGE', null)
