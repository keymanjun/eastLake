<div id="dtree" name="dtree" class="dtree"></div>
<div align="left" class="commandArea"></div>
 <script language="Javascript">
	var sPath="<%=request.getContextPath()%>";
   	tree=new dTree(document.getElementById("dtree"),sPath);          	        	
   
   	tree.add(0,-1,'产品','#','产品');
   	var sdata="1:aa,0;2:bb,0;11:a,1;12:b,1;111:abc,11;3:cc,0;31:c,3;";
   	tree.data=sdata;
   	initDTree(tree);
</script>