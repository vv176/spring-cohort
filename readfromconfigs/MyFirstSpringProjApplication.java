package exceptionhandling.classroom.readfromconfigs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

// Convention over Config, annotation
@SpringBootApplication
@PropertySource("classpath:extra.properties")
// spring.profiles.active
public class MyFirstSpringProjApplication implements ApplicationRunner {

	@Value("${key}")
	private String myVal;

	@Value("${hostname}")
	private String myHost;

	@Value("${dynamodb.usrname}")
	private String ddbUsrName;

	public static void main(String[] args) {
		ApplicationContext ctx =
		 SpringApplication.run(MyFirstSpringProjApplication.class, args);
	    for (String bean : ctx.getBeanDefinitionNames()) {
			System.out.println(bean);
		}
		System.out.println("=======");
		Object o = ctx.getBean("lifecycleProcessor");
		System.out.println(o.getClass().getName());
		com.example.demo.GameManager gameManager =
				(com.example.demo.GameManager)ctx.getBean("MyManager");
		gameManager.manage();
		AWS aws =  (AWS)ctx.getBean("AWS");
		System.out.println(aws.getUsrName() + "|" + aws.getPassWd());
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(myVal + "|" + myHost + "|" + ddbUsrName);
	}
}
// don't do anything on their own
// 1. Load all the Beans
// 2. Event Handling

// HTTP : Hyper-Text (Structured-Text(components) + Links) GET /.index.html
// Stateless protocol : server doesnot store any context/info about client in b/w 2 request
// Iterator : hasNext(), next()
// Paginated
// client      server
// Request : Method(GET, POST, PUT,....), URI, version, Headers, Body()
// Response : StatusLine, headers, Body
// ServletContainer : Tomcat, Jetty
// Servlet(init, service, destroy) : let us serve a request
// Stand-alone
// embedded