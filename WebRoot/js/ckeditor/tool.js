/**
 * 设置富文本  该方法需要加载时就调用
 * @param name 传入textarea的name
 */ 
function editorText(name){
	return	CKEDITOR.replace(name);
}

/**
 * 获取富文本的内容
 * @param name 传入textarea的name
 */
function getData(name)
  {
   if(CKEDITOR.instances.hasOwnProperty(name)){
	   return CKEDITOR.instances[name].getData();
   }
   return null;
  }
  
/**
 * 设置富文本的内容 
 * @param name
 * @param value 
 * @returns
 */
  function setData(name,value)
  {
	  if(CKEDITOR.instances.hasOwnProperty(name)){
		   return CKEDITOR.instances[name].setData(value);
	   }
  }
  
  
  function insertText(name,value)
  {
	  if(CKEDITOR.instances.hasOwnProperty(name)){
		   return CKEDITOR.instances[name].insertText( value );
	   }
  }
  
  
  function insertHtml(name,value)
  {
	  if(CKEDITOR.instances.hasOwnProperty(name)){
		   return CKEDITOR.instances[name].insertHtml( value );
	   }
  }