<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	  <!-- ECharts单文件引入 -->
    <!-- <script src="http://echarts.baidu.com/build/dist/echarts.js"></script> -->
    <script src="//cdn.bootcss.com/echarts/3.0.0/echarts.min.js"></script>
	<style type="text/css">
		body{
			padding-top: 70px; 
			padding-botton: 70px; 
		}
	   .footer p {
		    color: #7f8c8d;
		    margin: 0;
		    padding: 15px 0;
		    text-align: center;
		}
	</style>
	<!-- <link href="/stylesheets/basic.css" rel="stylesheet" type="text/css"> -->
  </head>
  <body>
	  <div class="container">
	  	<div class="row" id="nav">
	  		<tiles:insertAttribute name="nav"/>
	  	</div>
	    <div class="row" id="content">
	    	<tiles:insertAttribute name="content" /> 
	    </div>
	    <div class="row"  id="footer">
	    	<tiles:insertAttribute name="footer" /> 
	    </div>
	  </div>
  </body>
</html>