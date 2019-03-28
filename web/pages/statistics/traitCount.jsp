<%--
  Created by IntelliJ IDEA.
  User: big
  Date: 2019/3/19
  Time: 上午9:47
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

    <title></title>
</head>
<body>

<table class="table table-hover table-stripped table-bordered" style="width: 1000px;" align="center">
    <thead>
    <tr>
        <td>Trait Class</td>
        <td>SubTrait#</td>
        <td>Publications#</td>
        <td>Studys#</td>
        <td>Association#</td>
    </tr>
    </thead>
    <tbody>
    <s:iterator id="tableB" value="statisticBList">
        <tr>
            <td><s:property value="#tableB.traitId"/></td>
            <td><s:property value="#tableB.subTraitNum"/></td>
            <td><s:property value="#tableB.pubNum"/></td>
            <td><s:property value="#tableB.stdNum"/></td>
            <td><s:property value="#tableB.assNum"/></td>
        </tr>
    </s:iterator>
    </tbody>
</table>

</body>
</html>
