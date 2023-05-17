<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>任务管理系统</title>
    <meta name="description" content="任务管理系统">
    <meta name="keywords" content="任务管理系统">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel=“stylesheet”
          href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="../plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="../plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="../plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="../plugins/morris/morris.css">
    <link rel="stylesheet"
          href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="../plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="../plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="../plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="../plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="../plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="../plugins/select2/select2.css">
    <link rel="stylesheet"
          href="../plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="../plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="../plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="../css/style.css">
    <link rel="stylesheet"
          href="../plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="../plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="deptHeader.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="deptAside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                项目管理 <small>查询项目</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/toDeptMain"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="/toDeptMain">项目管理</a></li>
                <li class="active">查询项目</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!--订单信息-->


            <!--游客信息/--> <!--联系人信息-->
            <div class="panel panel-default">
                <div class="panel-heading">项目信息详情</div>
                <div class="row data-type">

                    <div class="col-md-2 title">项目名称</div>
                    <div class="col-md-4 data text">${currentTask.task_name}</div>

                    <div class="col-md-2 title">项目描述</div>
                    <div class="col-md-4 data text">${currentTask.task_description}</div>

                    <div class="col-md-2 title">开始时间</div>
                    <div class="col-md-4 data text">${currentTask.task_begin_time}</div>

                    <div class="col-md-2 title">结束时间</div>
                    <div class="col-md-4 data text">${currentTask.task_end_time}</div>

                    <div class="col-md-2 title">实施人</div>
                    <div class="col-md-4 data text">${currentTask.name}</div>

                    <div class="col-md-2 title">项目状态</div>
                    <div class="col-md-4 data text">${currentTask.task_state}</div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">任务信息</div>
                <c:forEach items="${planList}" var="plan" varStatus="status">
                    <span>计划${status.count}</span>
                    <div class="row data-type">

                        <div class="col-md-2 title">任务名称</div>
                        <div class="col-md-4 data text">${plan.plan_name}</div>

                        <div class="col-md-2 title">完成状态</div>
                        <div class="col-md-4 data text">${plan.plan_state}</div>

                        <div class="col-md-2 title">是否反馈</div>
                        <div class="col-md-4 data text">${plan.feedback}</div>

                        <div class="col-md-2 title">开始时间</div>
                        <div class="col-md-4 data text">${plan.plan_begin_date}</div>

                        <div class="col-md-2 title">结束时间</div>
                        <div class="col-md-4 data text">${plan.plan_end_date}</div>
                        <div class="col-md-2 title">操作</div>
                        <div class="col-md-4 data text">
                            <a href="/deptPlanShow?id=${plan.id}" class="btn bg-olive btn-xs">详细信息</a>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <div class="box-tools text-center">

                <button type="button" class="btn bg-default"
                        onclick="history.back(-1);">返回
                </button>
            </div>
            <!--工具栏/--> </section>
        <!-- 正文区域 /-->


    </div>
    <!-- 内容区域 /-->

</div>

<script
        src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="../plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
		$.widget.bridge('uibutton', $.ui.button);

</script>
<script
        src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="../plugins/raphael/raphael-min.js"></script>
<script
        src="../plugins/morris/morris.min.js"></script>
<script
        src="../plugins/sparkline/jquery.sparkline.min.js"></script>
<script
        src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
        src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
        src="../plugins/knob/jquery.knob.js"></script>
<script
        src="../plugins/daterangepicker/moment.min.js"></script>
<script
        src="../plugins/daterangepicker/daterangepicker.js"></script>
<script
        src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script
        src="../plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script
        src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script
        src="../plugins/fastclick/fastclick.js"></script>
<script
        src="../plugins/iCheck/icheck.min.js"></script>
<script
        src="../plugins/adminLTE/js/app.min.js"></script>
<script
        src="../plugins/treeTable/jquery.treetable.js"></script>
<script
        src="../plugins/select2/select2.full.min.js"></script>
<script
        src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script
        src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script
        src="../plugins/bootstrap-markdown/js/markdown.js"></script>
<script
        src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script
        src="../plugins/ckeditor/ckeditor.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script
        src="../plugins/datatables/jquery.dataTables.min.js"></script>
<script
        src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
<script
        src="../plugins/chartjs/Chart.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.resize.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.pie.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.categories.min.js"></script>
<script
        src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
<script
        src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script
        src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

<script>
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
		});

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}

		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("order-manage");

			// 列表按钮 
			$("#dataList td input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				increaseArea : '20%'
			});
			// 全选操作 
			$("#selall").click(function() {
				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
				}
				$(this).data("clicks", !clicks);
			});
		});

</script>
</body>


</html>