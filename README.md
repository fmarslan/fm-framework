# fm-framework

This framework developed for your small projects or small microservices apps. (if you want use for your big projects, it will not trouble you when used). This framework only offers you structure of middleware you will improve by adding another libraries. for example you can make web application by adding jetty(or another embedding server). I don't like big framework because they add all things I need or not need and this situation be back as complexity and slowness. so I developed this framework and I offers you for can use. if you have suggestion, I can evaluate it all time. Please you send me what is your suggestion or you can send merge request after developed it. My basic rule is  it must simple and plain, it should not include another dependencies. 

As my friend said if you need to comment, the code is mistake :) the code should be readable

# Skills

* Dynamic middleware adding
* Can create InvokeContext that the context includes information of request and response
* Allows for adding interceptor after and before method invoking
* Can adding custom exception handler or can use default exception handler
* Can adding custom response capsulation or default response capsulation
* Can use all classes there is not any rule


# How to Use

- Should added framework library to your project [for download click](https://github.com/fmarslan/fm-framework/raw/release/latest/fm-framework-latest.jar) [Maven](#Maven)
- You should improve middleware classes from BaseMiddleware
~~~
public class LoggingMiddleware extends BaseMiddleware{
	private static final long serialVersionUID = 4862224962026765025L;
	
	@Override
	public void before(InvokeContext<?> context) {
		. . .		
	}	
	@Override
	public void after(InvokeContext<?> context) {
		. . .
	}
}
~~~
- If you'll use custom exception handling, you should set your exception handling class to FMApplication
- If you'll use custom response capsulation, you should set your capsulation class to FMApplication
- You  add your middlewares you will use sequentially  by use FMAplication build method (for example FMAplication.build(new LoggingMiddleware()).build(new AuthMiddleware())).
- You create instance of ProxyService for classes you will use
- 



### Maven

~~~
<repositories>
	  . . .	 
	<repository>
		<id>myrepo</id>
		<url>https://raw.github.com/fmarslan/repository/master</url>
	</repository>
	   . . .
</repositories>

<dependency>
	<groupId>com.fmarslan</groupId>
	<artifactId>fm-framework</artifactId>
	<version>latest</version>
</dependency>
~~~
