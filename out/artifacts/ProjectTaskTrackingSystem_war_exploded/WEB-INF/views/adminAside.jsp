<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../img/user1-128x128.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${loginName}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 管理员</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a href="toAdminMain">
                <i class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>员工管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">

                    <li id="system-setting"><a
                            href="toAddUser"> <i
                            class="fa fa-circle-o"></i> 添加员工
                    </a></li>
                    <li id="system-setting"><a
                            href="findAll"> <i
                            class="fa fa-circle-o"></i> 查询员工
                    </a></li>
                    <li id="system-setting"><a
                            href="getNoAssignList">
                        <i class="fa fa-circle-o"></i> 分配员工
                    </a></li>
                </ul>
            </li>
            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>个人管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="showAdmin">
                        <i class="fa fa-circle-o"></i> 查看个人信息
                    </a></li>
                </ul>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="toAdminUpdateAdmin">
                        <i class="fa fa-circle-o"></i> 修改个人信息
                    </a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>