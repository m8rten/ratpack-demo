/*
 * Added repositories for grape, groovy dependency handler
 */
@GrabResolver(name="netty snapshots", root="http://clinker.netty.io/nexus/content/repositories/snapshots")
@GrabResolver(name="OJO", root="https://oss.jfrog.org/artifactory/repo")
/*
 * Added dependencies to download via grape
 */
@Grab("io.ratpack:ratpack-groovy:0.9.13")
@Grab("io.ratpack:ratpack-jackson:0.9.12")  

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.jsonNode
import ratpack.jackson.JacksonModule          

ratpack {

	/*
	 * What http handlers to use  
	 */
    handlers {

        /*
		 * Super simple get method, returns string
		 */
        get {
            response.send "Ratpack demo"
        }
		
        /*
		 * Can use normal java code, returns date
		 */
		get("date") {
            response.send new Date().toString()
        }

        /*
		 * Add assets for HTTP access to public directory.
		 * public contains file.txt
		 */
        assets "public"

        /*
         * Saves post to file.txt
         */
        post("save-to-file") {
          def postBody = parse jsonNode()
      	  new File("public/file.txt").append("\n$postBody")
		  render "Ok!"
		}

		/*
         * Runs ps -ef on machine, retunrs output
         */
        get("ps-ef") {
        	def process = "ps -ef".execute()
            response.send process.text
        }

		/*
		 * Runs arbitrary command
		 */
        post("run-command") {
			def postBody = parse jsonNode()
			String command = postBody.command
			String scrubbedCommand = command.substring(1, command.length()-1)
        	def process = scrubbedCommand.execute()
            render process.text
		}
    }

    /*
	 * Bindings so that we can use parse jsonNode() below
	 */
	bindings {                                  
		add new JacksonModule()                   
	}
}
