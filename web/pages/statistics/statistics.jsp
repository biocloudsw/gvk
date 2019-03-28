<%--
  Created by IntelliJ IDEA.
  User: big-tiandm
  Date: 2019/3/14
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="GWAS,Genotype">
    <meta http-equiv="description" content="GWAS Atlas">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/gvk/css/common.css" rel="stylesheet" />

    <script src="/gvk/js/jquery-3.2.1.min.js"></script>
    <script src="/gvk/bootstrap/js/bootstrap.min.js"></script>

    <script src="/gvk/js/common.js"></script>

    <script src="/gvk/js/echarts.js"></script>


    <title>GWAS Atlas</title>

</head>
<body>
<div class="container">

    <jsp:include page="/inc/header.jsp" />

    <div id="main-info" class="row">
        <div class="col-md-12">
            <!-- You are now at... -->
            <ol class="breadcrumb">
                <li><a href="/gvk/index.jsp">GWAS Atlas</a></li>
                <li class="active">Statistics</li>
            </ol>
        </div>
    </div>

    <!-- Content -->
    <div class="panel panel-default" style="margin-top: 15px;">
        <div class="panel-body" style="padding: 15px 25px 15px 25px; ">

            <div class="row" style="padding-top: 15px;">
                <!-- Table 1 -->
                <div class="col-md-12">
                    <h4 style="color: #3482c1;margin-top: 0px" id="main count">Main Count</h4>
                    <!--jsp:include page="mainCount.jsp" /-->
                    <s:action name="gotoStatic" namespace="/statistics" executeResult="true" />

                </div>

                <!-- Figure 1 -->
                <div class="col-md-6">
                    <h4 style="color: #3482c1;margin-top: 0px" id="pub title">Publications</h4>
                    <div id="pubChart" style="width: 100%;min-height: 350px" ></div>



                </div>

                <!-- Figure 2 -->
                <div class="col-md-6">
                    <h4 style="color: #3482c1;margin-top: 0px" id="study title">Top 10 Traits</h4>
                    <div id="traitChart" style="width: 100%;min-height: 350px" ></div>

                </div>

                <!-- Table 2 -->
                <div class="col-md-12">
                    <h4 style="color: #3482c1;margin-top: 0px" id="trait">Traits</h4>
                    <s:action name="gotoStaticD" namespace="/statistics" executeResult="true" />

                </div>

            </div>

        </div>
    </div>
</div>




</body>

<script src="/gvk/js/statistics/traitChart.js"></script>
<script src="/gvk/js/statistics/pubChart.js"></script>

</html>
