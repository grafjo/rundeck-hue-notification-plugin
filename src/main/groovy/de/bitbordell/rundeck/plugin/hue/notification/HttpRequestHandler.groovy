package de.bitbordell.rundeck.plugin.hue.notification

import com.dtolabs.rundeck.core.execution.workflow.steps.FailureReason
import com.dtolabs.rundeck.core.execution.workflow.steps.StepException
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method


class HttpRequestHandler {

    static enum Reason implements FailureReason {
        NotFound, UnexepectedFailure, BadRequest
    }

    def postJson(url, requestBody) {

        println "Doing post request for URL: ${url}"

        def http = new HTTPBuilder(url)

        http.request(Method.PUT, ContentType.JSON) { req ->

            body = requestBody

            response.success = { resp ->
                println "status: ${resp.status}"
            }

            response.failure = { resp ->
                throw new StepException("Status code: ${resp.status} -> URL: ${url}", Reason.UnexepectedFailure)
            }

            response.'404' = { resp ->
                throw new StepException("Not found -> URL:${url}", Reason.NotFound)
            }
        }
    }

}
