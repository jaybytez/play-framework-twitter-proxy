package controllers;

import models.ApiMessageResponse;
import models.twitter.Statuses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.*;

import java.util.Optional;

public class TwitterProxyController extends Controller {

	public static F.Promise<Result> getStatuses(String screenName) {
		TwitterProxyApi twitterProxyApi = (TwitterProxyApi.getMock(request().queryString())) ? new TwitterProxyApiStub(): new TwitterProxyApiImpl();
		return twitterProxyApi.getStatuses(screenName).map(statuses -> {
			if (statuses.isPresent()) {
				return ok(Json.toJson(statuses.get()));
			}
			return notFound(Json.toJson(ApiMessageResponse.create("Query for statuses returned empty", "The statuses that were searched could not be found", 400, "http://sample/400")));
		});
	}

	public static F.Promise<Result> updateStatuses(String screenName, String status) {
		TwitterProxyApi twitterProxyApi = (TwitterProxyApi.getMock(request().queryString())) ? new TwitterProxyApiStub(): new TwitterProxyApiImpl();
		return twitterProxyApi.updateStatuses(screenName, status).map(statuses -> {
			if (statuses.isPresent()) {
				return ok(Json.toJson(statuses.get()));
			}
			return notFound(Json.toJson(ApiMessageResponse.create("Query for statuses returned empty", "The statuses that were searched could not be found", 400, "http://sample/400")));
		});
	}

}