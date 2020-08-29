package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Nauka extends Simulation {

  val httpProtocol = http
    .baseUrl("https://kariera-testera.pl") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Kariera testera") // A scenario is a chain of requests and pauses
    .exec(
      http("Enter main page")
        .get("/")
    )
    .pause(7) // Note that Gatling has recorded real time pauses
    .exec(
      http("Enter contact about site")
        .get("/strona")
    )


  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
