/**
*	邮箱校验
**/
function isEmail_t(obj){   
	var reg=/^\w{3,}@\w+(\.\w+)+$/;   
	return reg.test(obj);  
}


/**
 * 自定义验证框架对象
 * @returns {___anonymous3595_3597}
 */
function validateFormObject_t(){ 
	var object = new Object();
	//this.color = color;
	
	var errorCount = 0;
	/*** 校验字符串长度以及是否为空
	*	@param ele_id 输入原色ID
	*	@param title  标题
	*	@param isNull 是否必填   true必填                false非必填
	*	@param minLength  最小长度
	*	@param maxLength  最大长度
	*	@param fun  回调函数
	*
	*/
	object.checkLength = function(element,title,isNull,minLength,maxLength,fun) {
		var obj = $(element);
		var div_group = obj;
		
		while(div_group && !div_group.is("DIV")){
			div_group = div_group.parent();
		}
		
		var div = div_group.parent();
		
		var error = "";
		
		var val = $.trim(obj.text());
		
		obj.text(val);
		if(isNull && val.length==0){
			error = title+"不能为空";
		}else if(minLength && isNull && minLength >0 && val.length<minLength){
			error = "输入的"+title+"长度不能低于 "+minLength+"位";
		}else if(maxLength && maxLength >0 && val.length>maxLength){
			error = "输入的"+title+"长度不能超过 "+maxLength+"位";
		}
		if(error=="" && fun &&fun!=""){
			error  = fun();
		}
		if(error!=""){
			errorCount ++;
			this.addError(div,div_group,error);
		}else{
			this.removeError(div,div_group);
		}
		
	};

	/*** 检验数字大小范围以及是否为空
	*	@param ele_id 输入原色ID
	*	@param title  标题
	*	@param isNull 是否必填   true必填                false非必填
	*	@param minLength  最小长度
	*	@param maxLength  最大长度
	*	@param fun  回调函数
	*
	*/
	object.checkNumber = function(element,title,isNull,minLength,maxLength,fun) { 
		var obj = $(element);
		var div_group = obj;

		while(div_group && !div_group.is("DIV")){
			div_group = div_group.parent();
		}
		var div = div_group.parent();
		var error = "";
		
		var val = $.trim(obj.text());
		obj.text(val);

		if(isNull && val.length==0){
			error = title+"不能为空";
		} 
		
		if(error == ""){
			var isNumber = !isNaN(val);//判断是否为数字
			if(isNumber){
				var temp = val;
				val =  Number(val);
				if(temp.length>1 && temp.indexOf("0")==0){
					error = "输入的"+title+"格式有误";
				}else if(minLength && isNull && minLength >0 && val<minLength){
					error = "输入的"+title+"值不能小于 "+minLength;
				}else if(maxLength && maxLength >0 && val>maxLength){
					error = "输入的"+title+"值不能大于 "+maxLength;
				}else if(fun &&fun!=""){
					error  = fun();
				}
			}else{
				error = "输入的"+title+"格式有误";
			}
		}
		if(error!=""){
			errorCount ++;
			this.addError(div,div_group,error);
		}else{
			this.removeError(div,div_group);
		}
		
	};

	
	/**
	*	追加显示错误信息
	*/
	object.addError = function(div,div_group,error){
		this.removeError(div,div_group);
		div.addClass("error");
		div.addClass("error_style");
		var p = $("<span style='display:block;'></span>");
		p.text(error);
		div_group.append(p);
	};

	
	/**
	*	删除错误信息
	*/
	object.removeError = function(div,div_group){
		div.removeClass("error");
		div_group.children("span").remove();
	};


	/**
	*	获取表单验证的结果       true验证通过         false验证未完全通过
	*/
	object.validate = function(){
		if(errorCount==0){
			return true;
		}else{
			return false;
		}
	}; 
	return object;
}
 