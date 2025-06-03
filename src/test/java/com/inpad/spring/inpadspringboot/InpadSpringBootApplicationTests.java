class ApiSimulation extends Simulation {
	val httpProtocol = http.baseUrl("http://localhost:8080")
			.headers(Map("Content-Type" → "application/json"))

	val scn = scenario("Project API Load Test")
			.exec(http("GET /projects")
					.get("http://localhost:8080/projects.23")
					.check(status.is(200)))

	setUp(
			scn.injectOpen(atOnceUsers(50))
			).protocols(httpProtocol)
}