<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${loginName}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 组长</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a
                    href="/toDeptMain"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>项目管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>


            </a>
                <ul class="treeview-menu">

                    <li id="system-setting"><a
                            href="/deptTaskList"> <i
                            class="fa fa-circle-o"></i> 查看项目
                    </a></li>
                    <li id="system-setting"><a
                            href="/toDeptTaskAdd"> <i
                            class="fa fa-circle-o"></i> 制订项目
                    </a></li>
                    <li id="system-setting"><a
                            href="/deptTaskAdjustList">
                        <i class="fa fa-circle-o"></i> 调整项目
                    </a></li>

                    <li id="system-setting"><a
                            href="/deptTaskFollowList">
                        <i class="fa fa-circle-o"></i> 跟踪项目
                    </a></li>
                    <li id="system-setting"><a
                            href="/deptLookBefore"> <i
                            class="fa fa-circle-o"></i> 查询任务
                    </a></li>
                </ul>
            </li>
            <li id="admin-index"><a
                    href="/deptUserList"><i
                    class="fa fa-dashboard"></i> <span>小组成员</span></a></li>
            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>个人管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting"><a
                            href="/showDept">
                        <i class="fa fa-circle-o"></i> 查看个人信息
                    </a></li>
                </ul>
                <ul class="treeview-menu">

                    <li id="system-setting"><a
                            href="toDeptUpdateDept">
                        <i class="fa fa-circle-o"></i> 修改个人信息
                    </a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>