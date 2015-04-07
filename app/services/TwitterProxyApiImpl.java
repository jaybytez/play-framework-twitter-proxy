package services;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import models.twitter.Status;
import models.twitter.Statuses;
import org.slf4j.Logger;
import play.libs.F;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * The TwitterProxyApiImpl Interface class
 */
public class TwitterProxyApiImpl implements	TwitterProxyApi {

	private static final Logger LOGGER = play.Logger.underlying();

	@Override
	public F.Promise<Optional<Statuses>> getStatuses(String screenName) {
		List<Status> statuses = new ArrayList<Status>();
		
		Twitter twitter = getTwitterInstance();

		return async(() -> {
			try {
				List<twitter4j.Status> twitterStatuses = twitter.getUserTimeline(screenName);
				for (Iterator<twitter4j.Status> it = twitterStatuses.iterator(); it.hasNext(); ) {
					statuses.add(new Status(it.next()));
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Twitter statuses were mediated into {}", statuses);
				}
			} catch (TwitterException e) {
				LOGGER.debug("An exception occurred and it is being logged to verify the root cause {}", e);
				throw new RuntimeException(e);
//			throw new WebApplicationException(e, Response.Status.SERVICE_UNAVAILABLE);
			}
			return Optional.of(new Statuses(statuses));
		});
	}

	private Twitter getTwitterInstance() {
		Config conf = ConfigFactory.load();
		// The factory instance is re-useable and thread safe.
		Twitter twitter = new TwitterFactory().getInstance();

		// insert the appropriate consumer key and consumer secret here
		twitter.setOAuthConsumer(conf.getString("oauth.consumerKey"), conf.getString("oauth.consumerSecret"));
				
		AccessToken accessToken = new AccessToken(
				conf.getString("oauth.accessToken"),
				conf.getString("oauth.accessTokenSecret"));
		twitter.setOAuthAccessToken(accessToken);
		
		return twitter;
	}

	@Override
	public F.Promise<Optional<Statuses>> updateStatuses(final String screenName, String status) {
		Twitter twitter = getTwitterInstance();
		return async(() -> {
			try {
				LOGGER.debug("Twitter status will be updated with {}", status);
				return F.Promise.pure(twitter.updateStatus(status));
			} catch (TwitterException e) {
				LOGGER.debug("An exception occurred and it is being logged to verify the root cause {}", e);
				throw new RuntimeException(e);
//			throw new WebApplicationException(e, Response.Status.SERVICE_UNAVAILABLE);
			}
		}).flatMap(updatedStatus -> getStatuses(screenName));
	}

}