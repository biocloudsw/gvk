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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/gvk/css/jstree/style.min.css" rel="stylesheet" />
    <link href="/gvk/css/common.css" rel="stylesheet" />
    <link href="/gvk/js/jquery-ui.css" rel="stylesheet" />
    <script src="/gvk/js/jquery-3.2.1.min.js"></script>
    <script src="/gvk/js/jquery-ui.js"></script>
    <script src="/gvk/js/jstree.min.js"></script>
    <script src="/gvk/js/common.js"></script>
    <script src="/gvk/js/echarts.js"></script>


    <title>GWAS Atlas</title>

</head>
<body>
<div class="container-fluid">

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

                    <table class="table table-hover table-stripped table-bordered" style="width: 1000px;" align="center">
                        <thead>
                        <tr>
                            <td>Species</td>
                            <td>Studies</td>
                            <td>Publications</td>
                            <td>Traits#</td>
                            <td>Association#</td>
                        </tr>
                        </thead>
                        <tbody>
                            <s:iterator id="tableA" value="statisticAList">
                            <tr>
                                <td><s:property value="#tableA.orgId"/></td>
                                <td><s:property value="#tableA.stdNo"/></td>
                                <td><s:property value="#tableA.pbNo"/></td>
                                <td><s:property value="#tableA.ttNo"/></td>
                                <td><s:property value="#tableA.asNo"/></td>
                            </tr>
                            </s:iterator>
                        </tbody>
                    </table>

                </div>

                <!-- Figure 1 -->
                <div class="col-md-6">
                    <h4 style="color: #3482c1;margin-top: 0px" id="pub title">Publications</h4>
                    <div id="pubchart" style="width: 100%;min-height: 350" ></div>

                    <script type="text/javascript">
                        $.ajax({
                            url: "/gvk/statisticsajax/gotoStaticajax",
                            type: "GET",
                            success: function (data) {
                                var myChart = echarts.init(document.getElementById('pubchart'));

                                option = {
                                    tooltip: {},

                                    // toolbox: {
                                    //     show: true,
                                    //     feature: {
                                    //         saveAsImage: {
                                    //             show:true,
                                    //             pixelRatio: 8
                                    //         }
                                    //     }
                                    // },

                                    radius: '100%',
                                    title: {
                                        text: 'Number of Publications',
                                        left:'left'
                                    },

                                    grid:{
                                        x2:10,
                                        left: 80,
                                    },

                                    xAxis: {
                                        type: 'category',

                                        nameGap: 30,
                                        name: "Year",
                                        data: data.pubYear2,
                                        // axisLabel:{
                                        //     interval: 0,
                                        //     rotate: 30,
                                        //     textStyle: {
                                        //         "fontSize": 18
                                        //     }
                                        // }
                                    },

                                    yAxis: {
                                        type: 'value',
                                        nameLocation: "center",
                                        nameGap: 30,
                                        name: "Number of publications",
                                        splitLine:{show: true},
                                        nameTextStyle: {
                                            fontSize: 15,
                                            fontFamily: 'Arial',
                                            fontWeight: 'bold'
                                        },
                                        // axisLabel : {
                                        //     fontSize: 18
                                        // }
                                    },
                                    series: [{
                                        data: data.yearCount,
                                        type: 'line',
                                        clickable : true,
                                        symbolSize: 10,
                                        label: {
                                            show: true,
                                            position: "top",
                                            textStyle: {
                                                "fontSize": 15
                                            }
                                        }

                                    }]
                                };


                                myChart.setOption(option);

                            }
                        })


                    </script>


                </div>

                <!-- Figure 2 -->
                <div class="col-md-6">
                    <h4 style="color: #3482c1;margin-top: 0px" id="study title">Studys</h4>
                    <div id="study" style="width: 100%;min-height: 350px" ></div>

                </div>

                <!-- Table 2 -->
                <div class="col-md-12">
                    <h4 style="color: #3482c1;margin-top: 0px" id="trait">Traits</h4>
                    <table class="table table-hover table-stripped table-bordered" style="width: 1000px;" align="center">
                        <thead>
                        <tr>
                            <td>Trait</td>
                            <td>Trait Class</td>
                            <td>Publications</td>
                            <td>Association#</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                        </tr>
                        <tr>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                        </tr>

                        </tbody>
                    </table>

                </div>

            </div>

        </div>
    </div>
</div>




</body>

<!--script src="/gvk/js/statistics/pub.js"></script-->
</html>
