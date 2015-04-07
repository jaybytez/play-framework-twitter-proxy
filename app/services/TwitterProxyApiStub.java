package services;

import models.twitter.Status;
import models.twitter.Statuses;
import org.slf4j.Logger;
import play.libs.F;

import java.util.Arrays;
import java.util.Optional;

/**
 * The TwitterProxyApiStub Interface class
 */
public class TwitterProxyApiStub implements
		TwitterProxyApi {

	private static final Logger LOGGER = play.Logger.underlying();

	@Override
	public F.Promise<Optional<Statuses>> getStatuses(String screenName) {
		Status status = new Status();
		status.setScreenName(screenName);
		return F.Promise.pure(Optional.of(new Statuses(Arrays.asList(status))));
    }
	
	@Override
	public F.Promise<Optional<Statuses>> updateStatuses(String screenName, String status) {
		Statuses statuses = new Statuses();
		statuses.getStatuses().add(new Status(status));
		return F.Promise.pure(Optional.of(statuses));
	}

}
