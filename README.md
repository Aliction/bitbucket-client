# Bitbucket Client

This repository is build to work as a Java rest client for bitbucket cloud APIs V2

## Add dependency

```
      <dependency>
        <groupId>com.aliction</groupId>
        <artifactId>bitbucket-client</artifactId>
        <version>0.2.0</version>
      </dependency>
```

Check versions from maven central [Bitbucket-Client](https://mvnrepository.com/artifact/com.aliction/bitbucket-client)

## Usage
First you need to create an instance of `BitbucketV2API` class which expects 3 String input parameter:
* hostURL: your bitbucket api server url
* username: your bitbucket user name
* token: bitbucket API token

`BitbucketV2API bitbucket = new BitbucketV2API("https://api.bitbucket.org", "my-user","9876234b92g3479cf2geiywrtg62834r");`

This newly created bitbucket object will give you access to all the implemented APIs and their provided methods, for example:

* To get list of all public repositories on bitbucket:

`List<BitbucketRepository> userRepos = bitbucket.RepositoryAPI().getAllPublicRepositories();`

* To get the user object of the currently logged user:

`BitbucketUser user = bitbucket.UserAPI().getLoggedUser();`

* To get list of teams the user has admin role to:

`List<BitbucketTeam> teams = bitbucket.TeamAPI().getUserTeams(BitbucketRole.ADMIN);`

## JavaDoc

JavaDoc is coming soon [HERE](http://www.aliction.com/projects/bitbucket-client/javadoc)

## Contribution
The library is published while still work in progress for adding more APIs and handling more functions, you're valuable contribution will help adding more features to the library, contribution can be and not limited to:
* Opening issues towards the library for any missing functionality
* Opening new PRs for adding new functionality that you want to make it available to the public and set you as a contributor to the library
* Actively opening an issue if you found any bug or faced any issue while using the library
* Opening new PRs for enhancing documentation, JavaDoc, or the README instructions
* Opening issues for having any questions regarding the library usage
* Any other contribution scenario that is not mentioned above


## Troubleshooting

Sometimes if the your application has conflicting dependencies with other libraries, you may get exceptions while parsing json responses for `MessageBodyReader` Exceptions like below:

```Exception in thread "main" org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException: MessageBodyReader not found for media type=application/json;charset=utf-8, type=class com.aliction.gitproviders.bitbucket.objects.BitbucketCloudUser, genericType=class com.aliction.gitproviders.bitbucket.objects.BitbucketCloudUser.
	at org.glassfish.jersey.message.internal.ReaderInterceptorExecutor$TerminalReaderInterceptor.aroundReadFrom(ReaderInterceptorExecutor.java:208)
	at org.glassfish.jersey.message.internal.ReaderInterceptorExecutor.proceed(ReaderInterceptorExecutor.java:132)
	at org.glassfish.jersey.message.internal.MessageBodyFactory.readFrom(MessageBodyFactory.java:1071)
	at org.glassfish.jersey.message.internal.InboundMessageContext.readEntity(InboundMessageContext.java:850)
	at org.glassfish.jersey.message.internal.InboundMessageContext.readEntity(InboundMessageContext.java:784)
	at org.glassfish.jersey.client.ClientResponse.readEntity(ClientResponse.java:297)
	at org.glassfish.jersey.client.InboundJaxrsResponse$1.call(InboundJaxrsResponse.java:91)
	at org.glassfish.jersey.internal.Errors.process(Errors.java:292)
	at org.glassfish.jersey.internal.Errors.process(Errors.java:274)
	at org.glassfish.jersey.internal.Errors.process(Errors.java:205)
	at org.glassfish.jersey.process.internal.RequestScope.runInScope(RequestScope.java:365)
	at org.glassfish.jersey.client.InboundJaxrsResponse.runInScopeIfPossible(InboundJaxrsResponse.java:240)
	at org.glassfish.jersey.client.InboundJaxrsResponse.readEntity(InboundJaxrsResponse.java:88)
    ...

```
Adding the json media library can help solve this issue:

```   
	  <dependency>
        <groupId>org.glassfish.jersey.media</groupId>
        <artifactId>jersey-media-json-jackson</artifactId>
        <version>2.29.1</version>
      </dependency>
```


## License

This library is released for [Aliction](http://www.aliction.com) under Apache 2 License.
Check [License](LICENSE.txt) for more information.

## Additonal Resources

[Bitbucket Deprecating API V1 Announcement](https://developer.atlassian.com/cloud/bitbucket/deprecation-notice-v1-apis/)

[Announcing Bitbucket API V2](https://developer.atlassian.com/cloud/bitbucket/announcement-06-08-18-new-v2-apis/)

[Bitbucket API V2 Reference](https://developer.atlassian.com/bitbucket/api/2/reference/)
