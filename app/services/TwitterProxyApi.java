package services;

import models.twitter.Statuses;
import play.libs.F;

import java.util.Map;
import java.util.Optional;

/**
 * The TwitterProxyApi Interface class
 */
public interface TwitterProxyApi extends Async{

	public F.Promise<Optional<Statuses>> getStatuses(String screenName);

	public F.Promise<Optional<Statuses>> updateStatuses(String screenName, String status);

	static boolean getMock(Map<String, String[]> params) {
		String[] mockStr = params.get("mock");
		if(mockStr != null) {
			return Boolean.valueOf(mockStr[0]);
		}
		return false;
	}
}