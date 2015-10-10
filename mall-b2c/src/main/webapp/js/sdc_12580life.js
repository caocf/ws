//webtrends sdc tag; updated by Liguangyin 2011/1/20/1800
function WebTrends(){
	var that=this;
	this.dcsid="dcs4vqpi1gn99hjhj4ik4q8d7_4w9z";
	this.domain="221.178.251.194";
	this.timezone=8;
	this.fpcdom=".12580life.com";
	this.onsitedoms="";
	this.navigationtag="div,table";
	this.trackevents=true;
	this.trimoffsiteparams=true;
	this.enabled=true;
	this.i18n=true;
	this.fpc="WT_FPC";
	this.paidsearchparams="gclid";
	this.DCS={};
	this.WT={};
	this.DCSext={};
	this.images=[];
	this.index=0;	
	this.Branch="js";
	this.grDcsid="dcs4vqpi1gn99hjhj4ik4q8d7_4w9z";
	this.grPageList=",/,/obsh.html#home,";
	this.WT.Branch="jiangsu";
	this.exre=(function(){return(window.RegExp?new RegExp("dcs(uri)|(ref)|(aut)|(met)|(sta)|(sip)|(pro)|(byt)|(dat)|(p3p)|(cfg)|(redirect)|(cip)","i"):"");})();
	this.re=(function(){return(window.RegExp?(that.i18n?{"%25":/\%/g}:{"%09":/\t/g,"%20":/ /g,"%23":/\#/g,"%26":/\&/g,"%2B":/\+/g,"%3F":/\?/g,"%5C":/\\/g,"%22":/\"/g,"%7F":/\x7F/g,"%A0":/\xA0/g}):"");})();
}


WebTrends.prototype.dcsGetId=function(){
	if (this.enabled&&(document.cookie.indexOf(this.fpc+"=")==-1)&&(document.cookie.indexOf("WTLOPTOUT=")==-1)){
		document.write("<scr"+"ipt type='text/javascript' src='"+"http"+(window.location.protocol.indexOf('https:')==0?'s':'')+"://"+this.domain+"/"+this.dcsid+"/wtid.js"+"'><\/scr"+"ipt>");
	}
}


WebTrends.prototype.dcsGetCookie=function(name){
	var cookies=document.cookie.split("; ");
	var cmatch=[];
	var idx=0;
	var i=0;
	var namelen=name.length;
	var clen=cookies.length;
	for (i=0;i<clen;i++){
		var c=cookies[i];
		if ((c.substring(0,namelen+1))==(name+"=")){
			cmatch[idx++]=c;
		}
	}
	var cmatchCount=cmatch.length;
	if (cmatchCount>0){
		idx=0;
		if ((cmatchCount>1)&&(name==this.fpc)){
			var dLatest=new Date(0);
			for (i=0;i<cmatchCount;i++){
				var lv=parseInt(this.dcsGetCrumb(cmatch[i],"lv"));
				var dLst=new Date(lv);
				if (dLst>dLatest){
					dLatest.setTime(dLst.getTime());
					idx=i;
				}
			}
		}
		return unescape(cmatch[idx].substring(namelen+1));
	}
	else{
		return null;
	}
}


WebTrends.prototype.dcsGetCrumb=function(cval,crumb,sep){
	var aCookie=cval.split(sep||":");
	for (var i=0;i<aCookie.length;i++){
		var aCrumb=aCookie[i].split("=");
		if (crumb==aCrumb[0]){
			return aCrumb[1];
		}
	}
	return null;
}


WebTrends.prototype.dcsGetIdCrumb=function(cval,crumb){
	var id=cval.substring(0,cval.indexOf(":lv="));
	var aCrumb=id.split("=");
	for (var i=0;i<aCrumb.length;i++){
		if (crumb==aCrumb[0]){
			return aCrumb[1];
		}
	}
	return null;
}


WebTrends.prototype.dcsIsFpcSet=function(name,id,lv,ss){
	var c=this.dcsGetCookie(name);
	if (c){
		return ((id==this.dcsGetIdCrumb(c,"id"))&&(lv==this.dcsGetCrumb(c,"lv"))&&(ss==this.dcsGetCrumb(c,"ss")))?0:3;
	}
	return 2;
}


WebTrends.prototype.dcsFPC=function(){
	if (document.cookie.indexOf("WTLOPTOUT=")!=-1){
		return;
	}
	var WT=this.WT;
	var name=this.fpc;
	var dCur=new Date();
	var adj=(dCur.getTimezoneOffset()*60000)+(this.timezone*3600000);
	dCur.setTime(dCur.getTime()+adj);
	var dExp=new Date(dCur.getTime()+315360000000);
	var dSes=new Date(dCur.getTime());
	WT.co_f=WT.vtid=WT.vtvs=WT.vt_f=WT.vt_f_a=WT.vt_f_s=WT.vt_f_d=WT.vt_f_tlh=WT.vt_f_tlv="";
	if (document.cookie.indexOf(name+"=")==-1){
		if ((typeof(gWtId)!="undefined")&&(gWtId!="")){
			WT.co_f=gWtId;
		}
		else if ((typeof(gTempWtId)!="undefined")&&(gTempWtId!="")){
			WT.co_f=gTempWtId;
			WT.vt_f="1";
		}
		else{
			WT.co_f="2";
			var curt=dCur.getTime().toString();
			for (var i=2;i<=(32-curt.length);i++){
				WT.co_f+=Math.floor(Math.random()*16.0).toString(16);
			}
			WT.co_f+=curt;
			WT.vt_f="1";
		}
		if (typeof(gWtAccountRollup)=="undefined"){
			WT.vt_f_a="1";
		}
		WT.vt_f_s=WT.vt_f_d="1";
		WT.vt_f_tlh=WT.vt_f_tlv="0";
	}
	else{
		var c=this.dcsGetCookie(name);
		var id=this.dcsGetIdCrumb(c,"id");
		var lv=parseInt(this.dcsGetCrumb(c,"lv"));
		var ss=parseInt(this.dcsGetCrumb(c,"ss"));
		if ((id==null)||(id=="null")||isNaN(lv)||isNaN(ss)){
			return;
		}
		WT.co_f=id;
		var dLst=new Date(lv);
		WT.vt_f_tlh=Math.floor((dLst.getTime()-adj)/1000);
		dSes.setTime(ss);
		if ((dCur.getTime()>(dLst.getTime()+1800000))||(dCur.getTime()>(dSes.getTime()+28800000))){
			WT.vt_f_tlv=Math.floor((dSes.getTime()-adj)/1000);
			dSes.setTime(dCur.getTime());
			WT.vt_f_s="1";
		}
		if ((dCur.getDay()!=dLst.getDay())||(dCur.getMonth()!=dLst.getMonth())||(dCur.getYear()!=dLst.getYear())){
			WT.vt_f_d="1";
		}
	}
	WT.co_f=escape(WT.co_f);
	WT.vtid=(typeof(this.vtid)=="undefined")?WT.co_f:(this.vtid||"");
	WT.vtvs=(dSes.getTime()-adj).toString();
	var expiry="; expires="+dExp.toGMTString();
	var cur=dCur.getTime().toString();
	var ses=dSes.getTime().toString();
	document.cookie=name+"="+"id="+WT.co_f+":lv="+cur+":ss="+ses+expiry+"; path=/"+(((this.fpcdom!=""))?("; domain="+this.fpcdom):(""));
	var rc=this.dcsIsFpcSet(name,WT.co_f,cur,ses);
	if (rc!=0){
		WT.vtvs=WT.vt_f_s=WT.vt_f_d=WT.vt_f_a=WT.vt_f_tlh=WT.vt_f_tlv="";
		if (typeof(this.vtid)=="undefined"){
			WT.vtid="";
		}
		WT.vt_f=rc;
    }
}


WebTrends.prototype.dcsIsOnsite=function(host){
	if (host.length>0){
	    host=host.toLowerCase();
	    if (host==window.location.hostname.toLowerCase()){
		    return true;
	    }
	    if (typeof(this.onsitedoms.test)=="function"){
		    return this.onsitedoms.test(host);
	    }
	    else if (this.onsitedoms.length>0){
		    var doms=this.dcsSplit(this.onsitedoms);
		    var len=doms.length;
		    for (var i=0;i<len;i++){
			    if (host==doms[i]){
			        return true;
			    }
		    }
	    }
	}
	return false;
}


WebTrends.prototype.dcsEvt=function(evt,tag){
	var e=evt.target||evt.srcElement;
	while (e.tagName&&(e.tagName.toLowerCase()!=tag.toLowerCase())){
		e=e.parentElement||e.parentNode;
		e=e || {};
	}
	return e;
}


WebTrends.prototype.dcsNavigation=function(evt){
	var id="";
	var cname="";
	var elems=this.dcsSplit(this.navigationtag);
	var elen=elems.length;	
	var i,e,elem;
	for (i=0;i<elen;i++){
		elem=elems[i];
		if (elem.length){
			e=this.dcsEvt(evt,elem);
			id=(e.getAttribute&&e.getAttribute("id"))?e.getAttribute("id"):"";
			cname=e.className||"";
			if (id.length||cname.length){
				break;
			}
		}
	}
	return id.length?id:cname;
}


WebTrends.prototype.dcsBind=function(event,func){
	if ((typeof(func)=="function")&&document.body){
		if (document.body.addEventListener){
			document.body.addEventListener(event, func.wtbind(this), true);
		}
		else if(document.body.attachEvent){
			document.body.attachEvent("on"+event, func.wtbind(this));
		}
	}
}


WebTrends.prototype.dcsET=function(){
	var e=(navigator.appVersion.indexOf("MSIE")!=-1)?"click":"mousedown";
	this.dcsBind(e,this.dcsOffsite);
}


WebTrends.prototype.dcsETAdv=function(){
	var e=(navigator.appVersion.indexOf("MSIE")!=-1)?"click":"mousedown";
	this.dcsBind(e,this.dcsJavaScript);
	this.dcsBind(e,this.dcsPageLink);
	this.dcsBind(e,this.dcsFormButton);
	this.dcsBind(e,this.dcsAnchor);
	this.dcsBind(e,this.dcsImageMap);
}


WebTrends.prototype.trackEvent=function(){
	if (this.trackevents&&(typeof(this.dcsETAdv)=="function")){
		if (window.addEventListener){
			window.addEventListener("load",this.dcsETAdv.wtbind(this),false);
		}
		else if (window.attachEvent){
			window.attachEvent("onload",this.dcsETAdv.wtbind(this));
		}
	}
}


WebTrends.prototype.dcsMultiTrack=function(){
	var uri=this.DCS.dcsuri;
	var args=dcsMultiTrack.arguments?dcsMultiTrack.arguments:arguments;
	if (args.length%2==0){
		this.dcsVar();
		this.dcsSetProps(args);
		var dCurrent=new Date();
		this.DCS.dcsdat=dCurrent.getTime();
		this.dcsFPC();
		this.dcsTag();
		this.dcsDelProps(args);
	}
	this.DCS.dcsuri=uri;
}


WebTrends.prototype.dcsCleanUp=function(){
	this.DCS={};
	this.WT={};
	this.DCSext={};
	if (arguments.length%2==0){
		this.dcsSetProps(arguments);
	}
}


WebTrends.prototype.dcsSetProps=function(args){
	for (var i=0;i<args.length;i+=2){
		if (args[i].toUpperCase().indexOf('WT.')==0){
			this.WT[args[i].substring(3)]=args[i+1];
		}
		else if (args[i].toUpperCase().indexOf('DCS.')==0){
			this.DCS[args[i].substring(4)]=args[i+1];
		}
		else if (args[i].toUpperCase().indexOf('DCSEXT.')==0){
			this.DCSext[args[i].substring(7)]=args[i+1];
		}
	}
}


WebTrends.prototype.dcsDelProps=function(args){
	for (var i=0;i<args.length;i+=2){
		if (args[i].toUpperCase().indexOf('WT.')==0){
			this.WT[args[i].substring(3)]="";
		}
	}
}


WebTrends.prototype.dcsSplit=function(list){
	var items=list.toLowerCase().split(",");
	var len=items.length;
	for (var i=0;i<len;i++){
		items[i]=items[i].replace(/^\s*/,"").replace(/\s*$/,"");
	}
	return items;
}


WebTrends.prototype.dcsPageLink=function(evt){
	evt=evt||(window.event||"");
	if (evt&&((typeof(evt.which)!="number")||(evt.which==1))){
		var e=this.dcsEvt(evt,"A");
		if (e.href){
		    var hn=e.hostname?(e.hostname.split(":")[0]):"";
		    var pr=e.protocol||"";
		    if ( this.dcsIsOnsite(hn) && pr.toLowerCase() !="javascript:" && !(e.hash&&(e.hash!="")&&(e.hash!="#"))){
			    var qry=e.search?e.search.substring(e.search.indexOf("?")+1,e.search.length):"";
			    var pth=e.pathname?((e.pathname.indexOf("/")!=0)?"/"+e.pathname:e.pathname):"/";
			    this.dcsMultiTrack("WT.event","pageLink:"+e.href,"WT.dl","24","WT.nv",this.dcsNavigation(evt));
			    this.WT.event=this.WT.dl=this.WT.nv="";
		    }
		}
	}
}



WebTrends.prototype.dcsJavaScript=function(evt){
	evt=evt||(window.event||"");
	if (evt&&((typeof(evt.which)!="number")||(evt.which==1))){
		var e=this.dcsEvt(evt,"A");
		if (e.href&&e.protocol){
			var qry=e.search?e.search.substring(e.search.indexOf("?")+1,e.search.length):"";
			if (e.protocol.toLowerCase()=="javascript:"){
				this.dcsMultiTrack("WT.event","JavaScript:"+e.innerHTML,"WT.dl","22","WT.nv",this.dcsNavigation(evt));
				this.WT.event=this.WT.cl=this.WT.nv="";
			}
		}
	}
}

WebTrends.prototype.dcsFormButton=function(evt){
	evt=evt||(window.event||"");
	if (evt&&((typeof(evt.which)!="number")||(evt.which==1))){
		var tags=["INPUT","BUTTON"];
		for (var j=0;j<tags.length;j++){
			var e=this.dcsEvt(evt,tags[j]);
			var type=e.type||"";
			if (type&&((type=="submit")||(type=="image")||(type=="button")||(type=="reset"))||((type=="text")&&((evt.which||evt.keyCode)==13))){
				var uri="";
				var ttl="";
				var id=0;
				if (e.form){
					// begin: field capture
					// end: field capture
					uri=e.form.action||window.location.pathname;
					ttl=e.form.id||e.form.name||e.form.className||"Unknown";
					id=(e.form.method&&(e.form.method.toLowerCase()=="post"))?"27":"26";
				}
				else{
					uri=window.location.pathname;
					ttl=e.name||e.id||"Unknown";
					id=(tags[j].toLowerCase()=="input")?"28":"29";
				}
				if (uri&&ttl&&(evt.keyCode!=9)){
					this.dcsMultiTrack("WT.event","FormButton:"+ttl,"WT.dl",id,"WT.nv",this.dcsNavigation(evt));
				}
				this.WT.event=this.WT.dl=this.WT.nv="";
				break;
			}
		}
	}
}

WebTrends.prototype.dcsOffsite=function(evt){
	evt=evt||(window.event||"");
	if (evt&&((typeof(evt.which)!="number")||(evt.which==1))){
		var e=this.dcsEvt(evt,"A");
		if (e.href){
		    var hn=e.hostname?(e.hostname.split(":")[0]):"";
		    var pr=e.protocol||"";
		    if ((hn.length>0)&&(pr.indexOf("http")==0)&&!this.dcsIsOnsite(hn)){
			    var qry=e.search?e.search.substring(e.search.indexOf("?")+1,e.search.length):"";
			    var pth=e.pathname?((e.pathname.indexOf("/")!=0)?"/"+e.pathname:e.pathname):"/";
			    this.dcsMultiTrack("WT.event","offsite:"+e.href,"WT.dl","24","WT.nv",this.dcsNavigation(evt));
			    this.WT.event=this.WT.dl=this.WT.offsite=this.WT.nv="";
		    }
		}
	}
}


WebTrends.prototype.dcsAnchor=function(evt){
	evt=evt||(window.event||"");
	if (evt&&((typeof(evt.which)!="number")||(evt.which==1))){
		var e=this.dcsEvt(evt,"A");
		if (e.href){
		    var hn=e.hostname?(e.hostname.split(":")[0]):"";
		    if (this.dcsIsOnsite(hn)&&e.hash&&(e.hash!="")&&(e.hash!="#")){
		        var qry=e.search?e.search.substring(e.search.indexOf("?")+1,e.search.length):"";
			    var pth=e.pathname?((e.pathname.indexOf("/")!=0)?"/"+e.pathname:e.pathname):"/";
			    this.dcsMultiTrack("WT.event","Anchor:"+e.hash.substring(1,e.hash.length),"WT.dl","21","WT.nv",this.dcsNavigation(evt));
			    this.WT.event=this.WT.dl=this.WT.nv="";
		    }
		}
	}
}

WebTrends.prototype.dcsImageMap=function(evt){
	evt=evt||(window.event||"");
	if (evt){
		var e=this.dcsEvt(evt,"AREA");
		if (e.href){
		    var hn=e.hostname?(e.hostname.split(":")[0]):"";
		    if ((hn!="")&&e.protocol&&(e.protocol.indexOf("http")!=-1)){
			    var ttl="";
			    var map=this.dcsEvt(evt,"MAP");
			    if (map){
			        if (map.name){
				        ttl=map.name;
			        }
			        else if (map.id){
				        ttl=map.id;
			        }
			    }
			    var pth=e.pathname?((e.pathname.indexOf("/")!=0)?"/"+e.pathname:e.pathname):"/";
			    this.dcsMultiTrack("WT.event","ImageMap:"+ttl,"WT.dl","30","WT.nv",this.dcsNavigation(evt));
			    this.WT.event=this.WT.dl=this.WT.nv="";
		    }			
		}			
	}
}
WebTrends.prototype.dcsAdv=function(){
	if (this.trackevents&&(typeof(this.dcsET)=="function")){
		if (window.addEventListener){
			window.addEventListener("load",this.dcsET.wtbind(this),false);
		}
		else if (window.attachEvent){
			window.attachEvent("onload",this.dcsET.wtbind(this));
		}
	}
	this.dcsFPC();
}


WebTrends.prototype.dcsVar=function(){
	var dCurrent=new Date();
	var WT=this.WT;
	var DCS=this.DCS;
	DCS.dcsdat=dCurrent.getTime();
	DCS.dcssip=window.location.hostname;
	DCS.dcsuri=window.location.pathname;
	WT.es=window.location.href;
	WT.host=window.location.hostname;
	WT.anchor=window.location.hash!=""?window.location.hash.substring(1,window.location.hash.length):"";

	try{
		var mobile = null;
		var url = "http://www.js.10086.cn/loginhelp?callback=?";
		jQuery.getJSON(url, null, function call(result) {
			jQuery.each(result.data, function(i, user) {
				mobile = user.userName;
				if("" != mobile && mobile != null && mobile != "undefined"){
					WT.mobile = mobile;
				}
			});
		})
	}catch(e){
	}
	
	
	if (window.location.search){
		DCS.dcsqry=window.location.search;
	}
	if (DCS.dcsqry){
		var dcsqry=DCS.dcsqry.toLowerCase();
		var params=this.paidsearchparams.length?this.paidsearchparams.toLowerCase().split(","):[];
		for (var i=0;i<params.length;i++){
			if (dcsqry.indexOf(params[i]+"=")!=-1){
				WT.srch="1";
				break;
			}
		}
	}
	if ((window.document.referrer!="")&&(window.document.referrer!="-")){
		if (!(navigator.appName=="Microsoft Internet Explorer"&&parseInt(navigator.appVersion)<4)){
			DCS.dcsref=WT.referrer=window.document.referrer;
		}
	}
}


WebTrends.prototype.dcsEscape=function(S, REL){
	if (REL!=""){
		S=S.toString();
		for (var R in REL){
 			if (REL[R] instanceof RegExp){
				S=S.replace(REL[R],R);
 			}
		}
		return S;
	}
	else{
		return escape(S);
	}
}
WebTrends.prototype.dcsA=function(N,V){
	if (this.i18n&&(this.exre!="")&&!this.exre.test(N)){
		if (N=="dcsqry"){
			var newV="";
			var params=V.substring(1).split("&");
			for (var i=0;i<params.length;i++){
				var pair=params[i];
				var pos=pair.indexOf("=");
				if (pos!=-1){
					var key=pair.substring(0,pos);
					var val=pair.substring(pos+1);
					if (i!=0){
						newV+="&";
					}
					newV+=key+"="+this.dcsEncode(val);
				}
			}
			V=V.substring(0,1)+newV;
		}
		else{
			V=this.dcsEncode(V);
		}
	}
	return "&"+N+"="+this.dcsEscape(V, this.re);
}
WebTrends.prototype.dcsEncode=function(S){
	return (typeof(encodeURIComponent)=="function")?encodeURIComponent(S):escape(S);
}


WebTrends.prototype.dcsCreateImage=function(dcsSrc){
	if (document.images){
		this.images[this.index]=new Image();
		this.images[this.index].src=dcsSrc;
		this.index++;
	}
	else{
		document.write('<IMG ALT="" BORDER="0" NAME="DCSIMG" WIDTH="1" HEIGHT="1" SRC="'+dcsSrc+'">');
	}
}


WebTrends.prototype.dcsMeta=function(){
	var elems;
	if (document.all){
		elems=document.all.tags("meta");
	}
	else if (document.documentElement){
		elems=document.getElementsByTagName("meta");
	}
	if (typeof(elems)!="undefined"){
		var length=elems.length;
		for (var i=0;i<length;i++){
			var name=elems.item(i).name;
			var content=elems.item(i).content;
			var equiv=elems.item(i).httpEquiv;
			if (name.length>0){
				if (name.toUpperCase().indexOf("WT.")==0){
					this.WT[name.substring(3)]=content;
				}
				else if (name.toUpperCase().indexOf("DCSEXT.")==0){
					this.DCSext[name.substring(7)]=content;
				}
				else if (name.toUpperCase().indexOf("DCS.")==0){
					this.DCS[name.substring(4)]=content;
				}
			}
		}
	}
}


WebTrends.prototype.dcsTag=function(){
	if (document.cookie.indexOf("WTLOPTOUT=")!=-1){
		return;
	}
	var WT=this.WT;
	var DCS=this.DCS;
	var DCSext=this.DCSext;
	var i18n=this.i18n;
	var P="http"+(window.location.protocol.indexOf('https:')==0?'s':'')+"://"+this.domain+(this.dcsid==""?'':'/'+this.dcsid)+"/dcs.gif?";
	if (i18n){
		WT.dep="";
	}
	for (var N in DCS){
 		if (DCS[N]&&(typeof DCS[N]!="function")){
			P+=this.dcsA(N,DCS[N]);
		}
	}
	var keys=["co_f","vtid","vtvs","vt_f_tlv"];
	for (var i=0;i<keys.length;i++){
		var key=keys[i];
		if (WT[key]){
			P+=this.dcsA("WT."+key,WT[key]);
			delete WT[key];
		}
	}
	for (N in WT){
		if (WT[N]&&(typeof WT[N]!="function")){
			P+=this.dcsA("WT."+N,WT[N]);
		}
	}
	for (N in DCSext){
		if (DCSext[N]&&(typeof DCSext[N]!="function")){
			if (i18n){
				WT.dep=(WT.dep.length==0)?N:(WT.dep+";"+N);
			}
			P+=this.dcsA(N,DCSext[N]);
		}
	}
	if (i18n&&(WT.dep.length>0)){
		P+=this.dcsA("WT.dep",WT.dep);
	}
	if (P.length>2048&&navigator.userAgent.indexOf('MSIE')>=0){
		P=P.substring(0,2040)+"&WT.tu=1";
	}
	this.dcsCreateImage(P);
	if ( typeof(this.WT.si_n)!="undefined" && ",ZDCX,QDCX,ZHYEJYXQ,CZJF_CZJFJL,JFDHCX_JFCX,TCJYWCX,GRZLGL_PUKMCX,HMGSDCX,TCSYQK,LDTX,LDXS,TCJYWCX_FJGNBG_HJDD,TCJYWCX_FJGNBG_HJZY,MSFF,YYXX,JTZHKHJGL,IPZTC,GJTGACT,GJTGAMY,YHTFJ,PPTCBG,ZXRW_QPPZXRW,SJZF,YLKCZ,MMFW_MMXG,MMFW_MMCZ,".toUpperCase().indexOf(","+this.WT.si_n.toUpperCase()+",")>-1 )	{
		this.dcsCreateImage(P.replace(this.domain+"/"+this.dcsid,"sdc.10086.cn/dcs4vqpi1gn99hjhj4ik4q8d7_4w9z"));
	}


if ( this.grPageList!=",," && this.grPageList.toUpperCase().indexOf(","+this.DCS.dcsuri.toUpperCase()+",")>-1)	{
		this.dcsCreateImage(P.replace(this.domain+"/"+this.dcsid,"sdc.10086.cn/"+this.grDcsid));
	}
	
	this.WT.ad="";
}


WebTrends.prototype.dcsCollect=function(){
    if (this.enabled){
        this.dcsAdv();
        this.dcsTag();
    }
}


Function.prototype.wtbind = function(obj){
	var method=this;
	var temp=function(){
		return method.apply(obj,arguments);
	};
	return temp;
}


function dcsMultiTrack(){
	if (typeof(_tag)!="undefined"){
		return(_tag.dcsMultiTrack());
	}
}
var _tag=new WebTrends();
_tag.dcsVar();
_tag.dcsMeta();
if(document.attachEvent){
	document.onreadystatechange=function(){
		if (document.readyState=='complete'){_tag.dcsCollect();	}
	}
}else{
	document.addEventListener("DOMContentLoaded",function(){_tag.dcsCollect();},false);
}	
