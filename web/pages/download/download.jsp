<%--
  Created by IntelliJ IDEA.
  User: big
  Date: 2019/3/14
  Time: 下午2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="/gvk/js/headerfooter.js"></script>
    <script src="/gvk/js/common.js"></script>

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
                <li class="active">Download</li>
            </ol>
        </div>
    </div>

    <!-- Content -->
    <div class="panel panel-default" style="margin-top: 15px;">


        <div class="panel-body" style="padding-left: 3%; padding-right: 3%">
            All the following file can be downloaded freely.
            <hr/>

        <table  class="table table-bordered">
            <tr>
                <th>Description</th>
                <th>Download</th>
                <th>File size</th>
            </tr>
            <tr>
                <td>Ontology</td>
                <td><a href="" download="EWAS_Atlas_associations_v2_20181107.tsv">EWAS_Atlas_associations_v2_20181107.tsv</a></td>
                <td>31.9MB</td>
            </tr>
            <tr>
                <td>Maize Association</td>
                <td><a href="" download="EWAS_Atlas_studies_v2_20181107.tsv">EWAS_Atlas_studies_v2_20181107.tsv</a></td>
                <td>81KB</td>
            </tr>
            <tr>
                <td>Rice Association</td>
                <td><a href="" download="EWAS_Atlas_cohorts_v2_20181107.tsv">EWAS_Atlas_cohorts_v2_20181107.tsv</a></td>
                <td>208KB</td>
            </tr>
            <tr>
                <td>Soybean Association</td>
                <td><a href="" download="EWAS_Atlas_probe_annotations_v1_20180612.tsv">EWAS_Atlas_probe_annotations_v1_20180612.tsv</a></td>
                <td>170.9MB</td>
            </tr>
        </table>

        </div>
    </div>
</div>
</body>
</html>
