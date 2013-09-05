<html>
<head>
<meta charset="utf-8">
<title>QUnit Example</title>
<link rel="stylesheet" href="qunit.css">
</head>
<body>
<div id="qunit"></div>
<div id="qunit-fixture"></div>
<script src="qunit.js"></script>
<script src="tests.js"></script>
<script type="text/javascript">
	test( "hello test", function() {
	ok( 1 == "1", "Passed!" );
	});
</script>
</body>
</html>