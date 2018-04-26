  
    //var lastindex=($('#tb td').length/($('#tb tr').length-1))-1;//列的数量
    
    //var lineindex=($('#tb tr').length-1);  //行的数量
    
    //alert(lastindex)
    /****定义列按钮****/ 
	function rowDfine(tag){
		$('#row-def').show();
		$('#show').empty();  
        $('#hide').empty();  
        count=$('#'+tag).find('th').length-1;  
         for(i=0;i<count;i++){  
            if($('th:eq('+i+')').css('display')!='none'){  
                $('#show').append('<option value='+$('#'+tag).find('th:eq('+i+')').attr('class')+'>'+$('#'+tag).find('th:eq('+i+')').text()+'</option>') 
                $('#hide').append('<option value='+$('#'+tag).find('th:eq('+i+')').attr('class')+'>'+$('#'+tag).find('th:eq('+i+')').text()+'</option>') 
                $('#hide option').hide(); 
             }  
              else{  
                  
             }  
         }     
	}
    /****隐藏按钮****/
    function leftarrow(tag){
        value=[];  
         $('#show option:selected').each(function(){  
            value.push($(this).val());
            $(this).hide();  
        });
        for(i=0;i<value.length;i++){  
            if(value[i]!=null){
                $('#hide option[value^='+value[i]+']').show();
                $('.'+value[i]).css('display','none');
            }  
            else{  
                break;  
            }  
        }  
    }                           
    /****显示按钮****/      
     
    function rightarrow(){
        value=[];
         $('#hide option:selected').each(function(){  
             value.push($(this).val());
             $(this).hide();   
         });
         for(i=0;i<value.length;i++){  
             if(value[i]!=null){  
                $('.'+value[i]).css('display','');
                $('#show option[value^='+value[i]+']').show();
            }                      
            else{  
                 break;  
             }  
         }         
    }             
    /****隐藏所有按钮****/
    function leftarrowAll(){
        value=[];  
        $('#show option').each(function(){  
             value.push($(this).val());
             $(this).hide();    
        });  
         
        for(i=0;i<value.length;i++){  
            if(value[i]!=null){  
                $('#hide option').show();
                $('.'+value[i]).css('display','none');  
            }  
            else{  
                break;  
            }  
        }  
    }      
    
    /****显示所有按钮****/     
    function rightarrowAll(){        
        value=[]; 
        $('#hide option').each(function(){  
             value.push($(this).val()); 
             $(this).hide();  
        });
        for(i=0;i<value.length;i++){  
             if(value[i]!=null){  
                 $('.'+value[i]).css('display','');
                 $('#show option').show();
             }  
             else{  
                 break;  
             }  
        }   
    }  
                               
    /****上移按钮****/  
    function uparrow(tag){ 
    	 lineindex=$('#'+tag).find('tr').length-1;
         value=[];  
         $('#show option:selected').each(function(){  
             value.push($(this).val());  
         });  
         for(i=0;i<value.length;i++){  
             if(value[i]!=null){  
                rowindex=$('.'+value[i]).index();//获取当前td和th的索引位置
                 if(rowindex!=0){  
                    $('#'+tag).find('th:eq('+rowindex+')').insertBefore($('#'+tag).find('th:eq('+(rowindex-1)+')'));   
                    for(j=0;j<=lineindex;j++){  //循环每一行中选中的td
                        $('#'+tag).find('tr:eq('+j+') td:eq('+rowindex+')').insertBefore($('#'+tag).find('tr:eq('+j+') td:eq('+(rowindex-1)+')'));  
                     }
                     $('#show option:eq('+rowindex+')').insertBefore($('#show option:eq('+(rowindex-1)+')'));   
                 }  
                 else{  
                     break;  
                 }     
             }  
             else{  
                 break;  
             }  
         }     
    };  
                 
    /****下移按钮****/  
    function downarrow(tag){
    	lineindex=$('#'+tag).find('tr').length-1;
        value=[];  
         $('#show option:selected').each(function(){  
             value.push($(this).val());  
         });  
         totalindex=($('#show option').length-1);    
         for(i=0;i<value.length;i++){  
             if(value[i]!=null){  
                rowindex=$('.'+value[i]).index();  //获取当前td和th的索引位置 
                 if(rowindex!=totalindex){  
                    $('#'+tag).find('th:eq('+rowindex+')').insertAfter($('#'+tag).find('th:eq('+(rowindex+1)+')'));   
                     for(j=0;j<=lineindex;j++){   //循环每一行中选中的td
                        $('#'+tag).find('tr:eq('+j+') td:eq('+rowindex+')').insertAfter($('#'+tag).find('tr:eq('+j+') td:eq('+(rowindex+1)+')'));  
                     }  
                     $('#show option:eq('+rowindex+')').insertAfter($('#show option:eq('+(rowindex+1)+')'));   
                }  
                else{  
                    break;  
                }  
             }  
             else{  
                 break;  
             }
        }  
    };  
                              
    /****上移至顶部按钮****/  
    function toparrow(tag){
    	lineindex=$('#'+tag).find('tr').length-1;
         value=[];  
         $('#show option:selected').each(function(){  
             value.push($(this).val());  
         });
         for(i=0;i<value.length;i++){  
             if(value[i]!=null){  
                 rowindex=$('.'+value[i]).index();   //获取当前td和th的索引位置 
                 if(rowindex!=0){  
                    $('#'+tag).find('th:eq('+rowindex+')').insertBefore($('#'+tag).find('th:eq(0)'));   
                     for(j=0;j<=lineindex;j++){  //循环每一行中选中的td
                        $('#'+tag).find('tr:eq('+j+') td:eq('+rowindex+')').insertBefore($('#'+tag).find('tr:eq('+j+') td:eq(0)'));  
                     }  
                     $('#show option:eq('+rowindex+')').insertBefore($('#show option:eq(0)'));   
                 }  
                 else{  
                     break;  
                 }  
             }  
             else{  
                 break;  
             }
        }  
    };  
                              
    /****下移至底部按钮****/  
    function bottomarrow(tag){
    	lineindex=$('#'+tag).find('tr').length-1;
        value=[];  
         $('#show option:selected').each(function(){  
             value.push($(this).val());  
         });   
         totalindex=($('#show option').length-1);  
         for(i=0;i!=-1;i++){  
             if(value[i]!=null){  
                 rowindex=$('.'+value[i]).index();   
                 if(rowindex!=totalindex){  
                    $('#'+tag).find('th:eq('+rowindex+')').insertAfter($('#'+tag).find('th:eq('+totalindex+')'));   
                     for(j=0;j<=lineindex;j++){  
                        $('#'+tag).find('tr:eq('+j+') td:eq('+rowindex+')').insertAfter($('#'+tag).find('tr:eq('+j+') td:eq('+totalindex+')'));  
                     }  
                     $('#show option:eq('+rowindex+')').insertAfter($('#show option:eq('+totalindex+')'));   
                 }  
                 else{  
                     break;  
                 }  
             }  
             else{  
                 break;  
             }  
         }  
    }