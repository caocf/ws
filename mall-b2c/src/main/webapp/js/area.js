
var fullAreaName = function(area) {
    var l1 = AREA[getprov(area)];
    var result = l1.n + " ";
    var l2 = l1.s[getcity(area)];
    result += l2.n + " ";
    var l3 = l2.s[area];
    result += l3;
    return result;
}

function getprov(area) {
    return area == null || area.length == 0 ? null : area.substring(0, 2) + '0000';
}
function getcity(area) {
    return area == null || area.length == 0 ? null : area.substring(0, 4) + '00';
}

var adrsInit = function(_cmbProv,_cmbCity,_cmbArea, area){
    if (_cmbProv.length == 0) return;
    var cmbProv=_cmbProv[0];
    var cmbCity=_cmbCity[0];
    var cmbArea=_cmbArea[0];
    cmbProv.innerHTML='';
    cmbCity.innerHTML='';
    cmbArea.innerHTML='';

    function cmbslc(cmb,str){
        for(var i=0;i<cmb.options.length;i++){
            if(cmb.options[i].value==str){
                cmb.selectedIndex=i;return
            }
        }
    }
    function cmbAddOpt(cmb,str,val,obj){
        var option=document.createElement("OPTION");
        cmb.options.add(option);
        option.innerHTML=str;
        option.value=val;
        if(val==='320000'){
            option.selected=true;
        }
        option.obj=obj
    }
    function changec(){
        cmbArea.options.length=0;
        if(cmbCity.selectedIndex==-1)return;
        var item=cmbCity.options[cmbCity.selectedIndex].obj;
        cmbAddOpt(cmbArea, '==请选择地区==', '', null);
        $.each(item, function(index, value) {
            cmbAddOpt(cmbArea, value , index, null)
        });
        cmbslc(cmbArea,area)
    }
    function changeProv(){
        cmbCity.options.length=0;
        cmbCity.onchange=null;
        if(cmbProv.selectedIndex==-1)return;
        var item=cmbProv.options[cmbProv.selectedIndex].obj;
        cmbAddOpt(cmbCity, '==请选择城市==', '', {});
        $.each(item, function(index, value) {
            cmbAddOpt(cmbCity, value.n, index, value.s);
        });
        cmbslc(cmbCity, getcity(area));changec();
        cmbCity.onchange=changec
    }



    cmbAddOpt(cmbProv, '==请选择省份==', '', {});
    $.each(AREA, function(index, value) {
        cmbAddOpt(cmbProv, value.n, index, value.s);
    });

    cmbslc(cmbProv, getprov(area));
    changeProv();
    cmbProv.onchange=changeProv


};