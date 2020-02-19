package com.lunchAndLearn.docker_project;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DockerProject extends AbstractVerticle{

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		String host = "0.0.0.0";
		
		if(null != args && args[0] != "") {
			host = args[0];
		}
		
		vertx.deployVerticle("com.lunchAndLearn.docker_project.DockerProject", 
				new DeploymentOptions().setConfig(new JsonObject().put("host",host)), result -> {
					if (result.succeeded()) {
						System.out.println("Server started at 0.0.0.0:8080" );
					} else {
						System.out.println("Server failed to start: " + result.cause());
					}
				});
	}
	
	@Override
	public void start(Promise<Void> startPromise ) throws Exception {
		vertx.createHttpServer().requestHandler(req -> {
			req.response().end("Hello docker");
		}).listen(8080, context.config().getString("host"), handler ->{
			if(handler.succeeded()) {
				startPromise.complete();
			} else {
				startPromise.fail(handler.cause());
			}
		});
	}
}
