/**
 * 
 */
function Request( callback, url, method, params ) {									
	this.callback = callback;								
	this.url = url;								
	this.method = method;								
	this.params = params;								
	this.httpRequest = null;								
}									
									
Request.prototype = {									
	getXMLHttpRequest : function() {								
		if( window.ActiveXObject ) {							
			try {						
				this.httpRequest = new ActiveXObject( "Msxml2.XMLHTTP" );					
			} catch ( e ) {						
				try {					
					this.httpRequest = new ActiveXObject( "Microsoft.XMLHTTP" );				
				} catch( ex ) {					
					this.httpRequest = null;				
				}					
			}						
		} else if( window.XMLHttpRequest ) {							
									
			try {						
				this.httpRequest = new XMLHttpRequest();					
			} catch( e ) {						
				this.httpRequest = null;					
			}						
		} else {							
			this.httpRequest = null;						
		}							
	},								
									
	//callback, url만 던져도 됨								
	sendRequest : function () {								
									
		// XMLHttpRequest 생성							
		this.getXMLHttpRequest();							
									
		// callBack							
		this.httpRequest.onreadystatechange = this.callback;							
									
		// method							
		var httpMethod = this.method ? this.method : "GET";							
		if( httpMethod != "GET" && httpMethod != "POST" ) {							
			httpMethod = "GET";						
		}							
									
		// params							
		//var httpParams = ( params == null || params =="" ) ? null : encodeURI( params );							
		var httpParams = ( this.params == null || this.params =="" ) ? null :  this.params;							
									
		// url							
		var httpUrl = ( httpMethod == "GET" ) ? ( this.url + "?" + httpParams ) : this.url;							
									
		// 요청 전송							
		this.httpRequest.open( httpMethod, httpUrl, true );							
		this.httpRequest.setRequestHeader( "content-type", "application/x-www-form-urlencoded;charset=utf-8");							
									
		this.httpRequest.send( httpMethod == "POST" ? httpParams : null );							
	}								
									
}; // Request.prototype									
