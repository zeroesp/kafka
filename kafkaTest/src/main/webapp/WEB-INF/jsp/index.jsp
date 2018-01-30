<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kafka</title>
<script language="JavaScript">
	function procuceTest() {
		var form = document.producerForm;
		if(form.topicName.value == null || form.topicName.value == ""){
			alert("topicName을 입력해 주세요");
			return false;
		}
		if(form.topicGenNum.value == null || form.topicGenNum.value == ""){
			alert("topicGenNum을 입력해 주세요");
			return false;
		}
		form.submit();
	}
	
	function consumeTest() {
		var form = document.consumerForm;
		if(form.topicNameConsume.value == null || form.topicNameConsume.value == ""){
			alert("topicName을 입력해 주세요");
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
[kafkaTest]
</br>
producer
</br>
<form name="producerForm" action="/producer" method="post">
	topicName : <input name="topicName" type="text" value="test" maxlength="20"></br>
	topicGenNum : <input name="topicGenNum" type="text" value="5" maxlength="20"></br>
	<input type="button" onclick="procuceTest()" value="procuceTest"></br>
	${produceResult}
</form>
</br>
consumer
</br>
<form name="consumerForm" action="/consumer" method="post">
	topicName : <input name="topicNameConsume" type="text" value="test" maxlength="20"></br>
	<input type="button" onclick="consumeTest()" value="consumeTest"></br>
</form>

</body>
</html>