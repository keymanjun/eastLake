
//var contextpath;
//var actionvalue;
// Node object
function Node(id, pid, name, url, title, param,ischecked,target, icon, iconOpen, open) {
	this.id = id;
	this.pid = pid;
	this.name = name;
	this.url = url;
	this.title = title;
	this.param=param;
	this.ischecked=ischecked;
	this.target = target;
	this.icon = icon;
	this.iconOpen = iconOpen;
	this._io = open || false;
	this._is = false;
	this._ls = false;
	this._hc = false;
	this._ai = 0;
	this._p;
};


// Tree object
function dTree(objName,appPath) {
	this.config = {
		target					: null,
		folderLinks			: true,
		useSelection		: true,
		useCookies			: true,
		useLines				: true,
		useIcons				: true,
		useStatusText		: false,
		closeSameLevel	: false,
		inOrder					: false,
		
		//����checkbox�Ƿ���ʾ
		check:					true
	}
	this.icon = {
		root				: 'img/base.gif',
		folder			: 'img/folder.gif',
		folderOpen	: 'img/folderopen.gif',
		node				: 'img/folder.gif',
		empty				: 'img/empty.gif',
		line				: 'img/line.gif',
		join				: 'img/join.gif',
		joinBottom	: 'img/joinbottom.gif',
		plus				: 'img/plus.gif',
		plusBottom	: 'img/plusbottom.gif',
		minus				: 'img/minus.gif',
		minusBottom	: 'img/minusbottom.gif',
		nlPlus			: 'img/nolines_plus.gif',
		nlMinus			: 'img/nolines_minus.gif'
	};
	this.obj = objName;
	this.aNodes = [];
	this.aIndent = [];
	this.root = new Node(-1);
	this.selectedNode = null;
	this.selectedFound = false;
	this.completed = false;
	this.imgBasePath=appPath.split(",")[0];
	
	//contextpath=appPath.split(",")[0];
	//actionvalue=appPath.split(",")[1];
	
	this.initIcon();
};


dTree.prototype.initIcon=function()
{
	var imgBasePath=this.imgBasePath+"/admin/js/checkboxtree/";
	this.icon.root=imgBasePath+this.icon.root;
	this.icon.folder=imgBasePath+this.icon.folder;
	this.icon.folderOpen=imgBasePath+this.icon.folderOpen;
	this.icon.node=imgBasePath+this.icon.node;
	this.icon.empty=imgBasePath+this.icon.empty;
	this.icon.line=imgBasePath+this.icon.line;
	this.icon.join=imgBasePath+this.icon.join;
	this.icon.joinBottom=imgBasePath+this.icon.joinBottom;
	this.icon.plus=imgBasePath+this.icon.plus;
	this.icon.plusBottom=imgBasePath+this.icon.plusBottom;
	this.icon.minus	=imgBasePath+this.icon.minus;
	this.icon.minusBottom=imgBasePath+this.icon.minusBottom;
	this.icon.nlPlus=imgBasePath+this.icon.nlPlus;
	this.icon.nlMinus=imgBasePath+this.icon.nlMinus;
}

// Adds a new node to the node array
dTree.prototype.add = function(id, pid, name, url, title, param,target, icon, iconOpen, open) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title,param, target, icon, iconOpen, open);
};

// Open/close all nodes
dTree.prototype.openAll = function() {
	this.oAll(true);
};
dTree.prototype.closeAll = function() {
	this.oAll(false);
};

// Outputs the tree to the page
dTree.prototype.toString = function() {
	var str = '<div class="dtree">\n';
	if (document.getElementById) 
	{
	   if (this.config.useCookies) this.selectedNode = this.getSelected();
	   str += this.addNode(this.root);
	} 
	else {
		str += 'Browser not supported.';
	}
	str += '</div>';
	if (!this.selectedFound) this.selectedNode = null;
	this.completed = true;
	return str;
};

// Creates the tree structure
dTree.prototype.addNode = function(pNode) {
	var str = '';
	var n=0;
	if (this.config.inOrder) n = pNode._ai;
	for (n; n<this.aNodes.length; n++) 
	{
		if (this.aNodes[n].pid == pNode.id) 
		{
			var cn = this.aNodes[n];
			cn._p = pNode;
			cn._ai = n;
			this.setCS(cn);
			if (!cn.target && this.config.target) cn.target = this.config.target;
			
			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);
			//if (!this.config.folderLinks && cn._hc) cn.url = null;
			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) 
			{
					cn._is = true;
					this.selectedNode = n;
					this.selectedFound = true;
			}
			str += this.node(cn, n);
			if (cn._ls) break;
		}
	}
	return str;
};

// Creates the node icon, url and text
dTree.prototype.node = function(node, nodeId) 
{
	var str = '<div class="dTreeNode" id='+this.obj.name+nodeId+'>' + this.indent(node, nodeId);	
  if(node.pid==-1)
  {
  	str+='<img src=" '+ this.icon.root +'"/>';
  }
	else
	{
		//����checkbox��Ԫ��
		if(this.config.check==true)
		{
	      str+= '<input type="checkbox" class="checkbox" id="c'+  this.obj.name + nodeId + '" ';
	     
	     str+=' onclick=tree.doChecked("'+nodeId+'")';
	      
	      //str+= ' value="'+node.name+'" onclick="'+this.obj.name+'.doClick('+nodeId+')"/>';
	      str+= ' value="'+node.name+'" pcode="'+node.pid+'">';
	  }
  }
  str += node.title;
	//str += '<a href="#" onclick=tree.showNodeInfo("'+node.name+'","'+actionvalue+'")>'+node.title+'</a>'
	str += '</div>';
	
	if (node._hc) 
	{
		//alert("this.root.id:"+this.root.id+"  node.pid:"+node.pid +"   node._io:"+node._io);
		str += '<div id="d' + this.obj.name + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';
		str += this.addNode(node);
		str += '</div>';
	} 
	this.aIndent.pop();
	return str;
};

// Adds the empty and line icons
dTree.prototype.indent = function(node, nodeId) 
{
	var str = '';
	if (this.root.id != node.pid) 
	{
		//_io
		//alert(this.aIndent.length);
		for (var n=0; n<this.aIndent.length; n++)
		{		    
			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />\n';
		  
		}
		
		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);
		
		if (node._hc ) 
		{
			str += '<a href="javascript: tree.o(' + nodeId + ');"><img id="j' + this.obj.name + nodeId + '" src="';
			
			//str += '<a href="#">';
			//str += '<img id="j' + this.obj.name + nodeId + '" src="';
		   	
			if (!this.config.useLines) {
			  str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;
			}
			else {
				var isbl=node._ls && this.config.useLines;
				
			   str += ( (node._io) ? (isbl ? this.icon.minusBottom : this.icon.minus) : (isbl ? this.icon.plusBottom : this.icon.plus ) );
			}
			str += '" alt="" /></a>\n';
		} 
		else 
		{
		   str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />\n';
		}
	}
	return str;
};

// Checks if a node has any children and if it is the last sibling
dTree.prototype.setCS = function(node) 
{
	var lastId;
	for (var n=0; n<this.aNodes.length; n++) 
	{
		if (this.aNodes[n].pid == node.id) node._hc = true;
		
		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;
	}	
	if (lastId==node.id) node._ls = true;	
};

// Returns the selected node
dTree.prototype.getSelected = function() {
	var sn = this.getCookie('cs' + this.obj);
	return (sn) ? sn : null;
};

// Highlights the selected node
dTree.prototype.s = function(id) {
	if (!this.config.useSelection) return;
	var cn = this.aNodes[id];
	if (cn._hc && !this.config.folderLinks) return;
	if (this.selectedNode != id) {
		if (this.selectedNode || this.selectedNode==0) {
			eOld = document.getElementById("s" + this.obj + this.selectedNode);
			eOld.className = "node";
		}
		eNew = document.getElementById("s" + this.obj + id);
		eNew.className = "nodeSel";
		this.selectedNode = id;
		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);
	}
};

// Toggle Open or close
dTree.prototype.o = function(id) {	
	var cn = this.aNodes[id];
	this.nodeStatus(!cn._io, id, cn._ls);
	cn._io = !cn._io;
	if (this.config.closeSameLevel) this.closeLevel(cn);
	//if (this.config.useCookies) this.updateCookie();
};

// Open or close all nodes
dTree.prototype.oAll = function(status) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {
			this.nodeStatus(status, n, this.aNodes[n]._ls)
			this.aNodes[n]._io = status;
		}
	}
	if (this.config.useCookies) this.updateCookie();
};

// Opens the tree to a specific node
dTree.prototype.openTo = function(nId, bSelect, bFirst) {
	if (!bFirst) {
		for (var n=0; n<this.aNodes.length; n++) {
			if (this.aNodes[n].id == nId) {
				nId=n;
				break;
			}
		}
	}
	var cn=this.aNodes[nId];
	if (cn.pid==this.root.id || !cn._p) return;
	cn._io = true;
	cn._is = bSelect;
	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);
	if (this.completed && bSelect) this.s(cn._ai);
	else if (bSelect) this._sn=cn._ai;
	this.openTo(cn._p._ai, false, true);
};

// Closes all nodes on the same level as certain node
dTree.prototype.closeLevel = function(node) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {
			this.nodeStatus(false, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = false;
			this.closeAllChildren(this.aNodes[n]);
		}
	}
}

// Closes all children of a node
dTree.prototype.closeAllChildren = function(node) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {
			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = false;
			this.closeAllChildren(this.aNodes[n]);		
		}
	}
}

// Change the status of a node(open or closed)
dTree.prototype.nodeStatus = function(status, id, bottom) 
{
	eDiv	= document.getElementById('d' + this.obj.name + id);
	eJoin	= document.getElementById('j' + this.obj.name + id);
	//alert(this.config.useLines+"  status:"+status+"  bottom:"+bottom);
	eJoin.src = (this.config.useLines)?
	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):
	((status)?this.icon.nlMinus:this.icon.nlPlus);
	eDiv.style.display = (status) ? 'block': 'none';
};


// [Cookie] Clears a cookie
dTree.prototype.clearCookie = function() {
	var now = new Date();
	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
	this.setCookie('co'+this.obj, 'cookieValue', yesterday);
	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);
};

// [Cookie] Sets value in a cookie
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {
	document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};

// [Cookie] Gets a value from a cookie
dTree.prototype.getCookie = function(cookieName) {
	var cookieValue = '';
	var posName = document.cookie.indexOf(escape(cookieName) + '=');
	if (posName != -1) {
		var posValue = posName + (escape(cookieName) + '=').length;
		var endPos = document.cookie.indexOf(';', posValue);
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
		else cookieValue = unescape(document.cookie.substring(posValue));
	}
	return (cookieValue);
};

// [Cookie] Returns ids of open nodes as a string
dTree.prototype.updateCookie = function() {
	var str = '';
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {
			if (str) str += '.';
			str += this.aNodes[n].id;
		}
	}
	this.setCookie('co' + this.obj, str);
};

// [Cookie] Checks if a node id is in a cookie
dTree.prototype.isOpen = function(id) {
	var aOpen = this.getCookie('co' + this.obj).split('.');
	for (var n=0; n<aOpen.length; n++)
		if (aOpen[n] == id) return true;
	return false;
};

// If Push and pop is not implemented by the browser
if (!Array.prototype.push) 
{
	Array.prototype.push = function array_push() 
	{
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
};
if (!Array.prototype.pop) 
{
	Array.prototype.pop = function array_pop() 
	{	
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
};


function clearCookie()
{
  dto1.clearCookie();
  dto2.clearCookie();
}
window.onunload=clearCookie;






/*** ---------------------add by feiluo   ����Ϊ��չ�������ڵ㷽�����¼�,��֧��ajax��̬��ȡ����  ---------------***/
dTree.prototype.initDTreeNode = function() 
{
	var arrdata=this.data.split(";");
	 for(var i=0;i<arrdata.length;i++)
	 {
	 	 if(arrdata[i]==null ||arrdata[i]=="") continue;
	 	 var arrs=arrdata[i].split(",");
	   this.add(arrs[0],arrs[1],arrs[0],'#',arrs[2]);	   
	 }
	 this.obj.innerHTML=this;
}


function getTreeCheckedValue()
{
	var sValue="";
	if(tree.aNodes==undefined) return;
	if(tree.aNodes.length>1)
	{
		for(var i=1;i<tree.aNodes.length;i++)
		{
			var obj = document.getElementById("c"+tree.obj.name+i);			
			if(obj==null) continue;
			if(obj.checked && obj.value.length>2)
			{
				sValue+=obj.value+",";
			}
		}  	
	}
	if(sValue.length>0)
	{
	  	sValue=sValue.substring(0,sValue.length-1);
	}
	return sValue;
}

//�����ӽڵ�ѡ��״̬
dTree.prototype.doChecked=function(nodeId)
{
  var cs = document.getElementById("c"+this.obj.name+nodeId).checked;
  var node = this.aNodes[nodeId]; 
  this.setToDownChecked(node,cs);
  if(cs==false){
    this.setToUpChecked(node);  
  }  
}

	 
dTree.prototype.setToDownChecked=function(node,nstatus)
{
	for(var n=0; n<this.aNodes.length; n++) 
  {
  	//to down
  	if (this.aNodes[n].pid == node.id) 
  	{
	      document.getElementById("c"+this.obj.name+n).checked=nstatus;	      
	      this.setToDownChecked(this.aNodes[n],nstatus);
	  }
  }
}

//���ø��ڵ�ѡ��״̬
dTree.prototype.setToUpChecked=function(node)
{	
  var len =this.aNodes.length;
  for (var m=0; m<len; m++) 
  {
  	if (this.aNodes[m].id == node.pid) {
  		  if(document.getElementById("c"+this.obj.name+m)!=null)
  		  {
	        document.getElementById("c"+this.obj.name+m).checked=false;
	        this.setToUpChecked(this.aNodes[m]);
	      }
	  }
  }
}
