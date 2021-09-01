<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}css/settlementstyle.css"  type = "text/css">
<script src="/project/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript">
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {

		var link = window.location.pathname;
		if(link = "/project/adminSettlement.mc"){
			link="/adminSettlementChart.mc";
		}else{
			link="/settlementChart.mc";
		}
		link="/testChart.mc";
		
	 $.ajax({
			url:'${pageContext.request.contextPath}'+link+'?${_csrf.parameterName}=${_csrf.token}',
			type:'POST', 
			dataType:"json",
			success:function(result){
			       var data = new google.visualization.DataTable();
			       //data.addColumn('string','hiredate');
			       data.addColumn('string','date');
				   data.addColumn('number','amount');
				   for (var i = 0; i < result.length-1; i++) {
	                    data.addRow([result[i].hiredate, result[i].sumorder]);
	               }
			       var options = {
			    	 fontSize: 9,
			         title: 'settlement',
			         hAxis: {title: 'date',  titleTextStyle: {color: '#333'}},
			         vAxis: {minValue: 0},
			         width: 600, // 넓이 옵션
					 height: 500
			       };
			       
			       var chart = new google.visualization.AreaChart(document.getElementById('chart'));
			       chart.draw(data, options);
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     }
		})
     
    }
</script>
<!-- date amount-->
<title>Insert title here</title>
</head>
<body>
	<div class="main">
	${pageContext.request.contextPath}<br>
	${request.getServletPath()}asd
		<%@ include file="managerHeader.jsp" %>
		<div class="bodyPage">
			<div class="bodyleft" id="chart">
				<!-- 
				<div class="progressMain">
				<c:forEach var="i" items="${list}" varStatus="j">
				<c:set var="date" value="${fn:split(i.hiredate,'-')}"/>
    						
					<div> 
						<c:if test="${!j.last}">
							<div class="progressText">${date[0]}년${date[1]}월</div>
							<progress class="progressbar" value="${i.sumorder}" max="${listMax}"></progress>
							<div class="progressText2"><fmt:formatNumber value="${i.sumorder }" pattern="#,###" /></div>
						</c:if>
					</div>
				</c:forEach>
				</div>
				-->
			</div>
			
			<div class="bodyright">
				<div class="count">
				 	<div>환불 ${approved==null?0:approved.get(1)}</div>
				 	<div>주문 ${approved==null?0:approved.get(0)}</div>
				 	<div>문의 0</div>
				</div>
				<div class="salesTable">
					<table cellspacing="0" >
						<tr>
							<th>이달매출</th>
							<td><fmt:formatNumber value="${list.get(11).sumorder}" pattern="#,###" /></td>
						</tr>
						<tr>
							<th>연간매출</th>
							<td><fmt:formatNumber value="${list.get(12).sumorder}" pattern="#,###" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>