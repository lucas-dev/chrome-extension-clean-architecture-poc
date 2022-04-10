# Chrome extension development with Clean Architecture (a PoC)
![enter image description here](https://cdn-images-1.medium.com/max/800/1*NF4bCQQnmz2t23j9wHdgBw.png)

If you have ever developed a Chrome extension you would be used to a solid ecosystem of well-stablished rules that guide development in a way that feels easy to come up with an up and running application in no time.

No architectural guidelines are imposed but then also that freedom **often** leads to project code structures that looks and feels like a big mess.

In this project I want to present a proof of concept for porting concepts and approaches related to clean architecture over to a chrome extension.

_Disclaimer: I’m focused to_ **_native Android development_** _and my experience in chrome extensions is not extensive. Some time ago I was required to develop and ship a companion extension for a mobile product on a very tight deadline. I followed amazing_ [_official docs_](https://developer.chrome.com/docs/extensions/mv3/getstarted/) _and_ [_tons of real projects_](https://github.com/topics/chrome-extension) _to acquire a consistent knowledge of this variant in web development. This post describes the approach I would use if I would have to rewrite that extension or create a new one._

## The app we will build

We are going to build a question and review search extension for products for sale on one of the top [amazon-like sites](http://www.mercadolibre.com.ar).

![](https://cdn-images-1.medium.com/max/800/1*JjeuAurPyBsTJcmUgHCgKA.gif)

The site exposes an extensive [api](https://developers.mercadolibre.com.ar/en_us/list-products) with convenient endpoints for fetching comments and reviews based on a product ID.

## A quick review of Chrome Extension development rules

At a glance, a chrome extension is made of the following script components:

-   Background: script acting as a [service worker](https://developer.chrome.com/docs/extensions/mv3/service_workers/) for performing tasks which lifecycle outlives the environment of any other window or tab
-   Content scripts: [scripts](https://developer.chrome.com/docs/extensions/mv3/content_scripts) allowing to read details and make changes to web pages the browser visits
-   Popup: script that just runs in the context of the popup windows displayed when the user clicks the extension icon.

Chrome provides a rich set of [apis](https://developer.chrome.com/docs/extensions/reference/) to interact with the browser, as well as an [entry point](https://developer.chrome.com/docs/extensions/mv3/manifest/) file for defining bunch of app configuration stuff.

The communication from one component to another is done by a [Message passing](https://developer.chrome.com/docs/extensions/mv3/messaging/) system.

So a typical workflow could be to parse DOM of current active webpage to fetch data, then from the very same content script send that data to background script to perform any potential long-term operation with it, when done, from background send response back either to popup script or content script to render data in the popup window or the active webpage respectively.

## Our PoC architecture

We will attempt to follow clean architecture principles based on its common Onion Model of Responsibilities.

Our application consists of three main **features**: [**item**](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/item) (representing a Product for sell), [**questions**](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions) and [**reviews**](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews). Each of them will be segmented into the common 3-tier [data-domain-presentation layering](https://martinfowler.com/bliki/PresentationDomainDataLayering.html) approach

**Data layer**

-   Defines interfaces related to data source manipulations.

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-data/src/main/kotlin/itemdata/sources/ItemHtml.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-data/src/main/kotlin/itemdata/sources/ItemStorage.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-data/src/main/kotlin/questionsdata/sources/QuestionsRemote.kt)][[4](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-data/src/main/kotlin/questionsdata/sources/QuestionsStorage.kt)][[5](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-data/src/main/kotlin/reviewsdata/sources/ReviewsRemote.kt)][[6](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-data/src/main/kotlin/reviewsdata/sources/ReviewsStorage.kt)]

-   Those interfaces will be realized in specialized modules.

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-html/src/main/kotlin/itemhtml/ItemHtmlImpl.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-storage/src/main/kotlin/itemstorage/ItemStorageImpl.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-remote/src/main/kotlin/questionsremote/QuestionsRemoteImpl.kt)][[4](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-storage/src/main/kotlin/questionsstorage/QuestionsStorageImpl.kt)][[5](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-remote/src/main/kotlin/reviewsremote/ReviewsRemoteImpl.kt)][[6](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-storage/src/main/kotlin/reviewsstorage/ReviewsStorageImpl.kt)]

-   Implement domain layer contracts by using those source interfaces

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-data/src/main/kotlin/itemdata/ItemRepositoryImpl.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-data/src/main/kotlin/questionsdata/QuestionsRepositoryImpl.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-domain/src/main/kotlin/reviewsdomain/repository/ReviewsRepository.kt)]

-   Depends on domain

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-data/item-data.gradle.kts)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-data/questions-data.gradle.kts)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-data/reviews-data.gradle.kts)]

**Domain layer**

-   Declares interfaces for data handling regarding business rules.

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-domain/src/main/kotlin/itemdomain/repository/ItemRepository.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-domain/src/main/kotlin/questionsdomain/repository/QuestionsRepository.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-domain/src/main/kotlin/reviewsdomain/repository/ReviewsRepository.kt)]

-   Data layer modules will implement those contracts.

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-data/src/main/kotlin/itemdata/ItemRepositoryImpl.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-data/src/main/kotlin/questionsdata/QuestionsRepositoryImpl.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-domain/src/main/kotlin/reviewsdomain/repository/ReviewsRepository.kt)]

-   Expose use-case type classes to interact with business contracts.

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-domain/src/main/kotlin/itemdomain/interactors/FetchAndStoreItem.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-domain/src/main/kotlin/questionsdomain/interactors/GetQuestionsFiltered.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-domain/src/main/kotlin/questionsdomain/interactors/GetQuestionsStored.kt)][[4](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-domain/src/main/kotlin/reviewsdomain/interactors/GetReviewsFiltered.kt)][[5](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-domain/src/main/kotlin/reviewsdomain/interactors/GetReviewsStored.kt)]

-   No dependencies on other layers

**Presentation layer**

Everything related to data visualization and user interaction

See: [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-presentation)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-ui)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-presentation)][[4](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-ui)]

We also have an [extra feature layer](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/core) composed by Chrome component modules: [background](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/core/background), [contentscript](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/core/content-script), [popup](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/core/popup).

The packaging pattern is **features by layers**.

Communication among these Chrome components is orchestrated via [FlowManager](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/common/common-flow/src/main/kotlin/commonflow/FlowManager.kt), a convenient actor implementing the [message passing system](https://developer.chrome.com/docs/extensions/mv3/messaging/) which lies in the [**common**](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/common) module.

#### Modularization

A convenient split of modules by feature layers is an often recommended approach for mid-to-big size projects and for this Kotlin DSL for Gradle comes with tons of features to make things a lot easier for us.

_See_ [_settings.gradle.kts_](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/settings.gradle.kts)

No doubts this kind of multi-module setup is overkill for such application, but as a PoC project, it’s worth demoing scalability.

## State Management with Unidirectional Data Flow

The presentation layer pattern I chose is a form of simplified mvi following unidirectional data(UDF) with states and events.

We will have a [single mutable field representing UI state](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/common/common-presentation/src/main/kotlin/commonpresentation/State.kt), and a [data class representing user commands](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/common/common-presentation/src/main/kotlin/commonpresentation/Events.kt).

So we would basically have a sort of _android-ish emulated ViewModel class (see [_[_1_](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-presentation/src/main/kotlin/questionspresentation/QuestionsViewModel.kt)_][_[_2_](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-presentation/src/main/kotlin/reviewspresentation/ReviewsViewModel.kt)_])_, which would have references to Domain interactors (bridge classes to repository data sources), and a public accessible function expecting an Event command that will interact with some interactor/s and then will emit into the State.

The [controller](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/core/popup/src/main/kotlin/popup/popup.kt) will hold a reference to that [ViewModel](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/core/popup/src/main/kotlin/popup/mvi/ViewModel.kt), will subscribe to that observable state property [State](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/core/popup/src/main/kotlin/popup/mvi/State.kt) and will render UI based on that mutated field. It will also send [events](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/core/popup/src/main/kotlin/popup/mvi/Events.kt) to the ViewModel.

Reactivity is managed through Kotlin Flows.

## Handling UI

It’s totally possible to delegate user interface building on declarative approaches like React or Compose, and that is actually recommended as they fit perfectly with this architecture, however for the sake of this PoC, I chose a way that resembles the good old imperative Android XML-ish style I’m used to work with.

So basically I would have an [interface declaring all widgets](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/common/common-ui/src/main/kotlin/commonui/Views.kt) to be afterwards implemented in feature modules by binding the DOM elements defined in the html document (see [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-ui/src/main/kotlin/questionsui/QuestionsViews.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-ui/src/main/kotlin/reviewsui/ReviewsViews.kt)]). Additionally I would have a few helper classes to handle interactions with those views (see [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-ui/src/main/kotlin/reviewsui/ReviewsViewManipulations.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-ui/src/main/kotlin/questionsui/QuestionsViewManipulations.kt)]).

This works OK but handing DOM interactions this way is against performance. Choose a declarative UI building approach if possible.

## Handling dependencies

I would have preferred to resort to an out-of-the-box DI framework like I would in Android with Hilt or Koin but unfortunately I didn’t have any luck finding some sample implementation, so in order to move forward, I had to implement a sort of service locator where I define an interface referencing values representing each component dependency (see [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/item/item-di/src/main/kotlin/itemdi/ItemServiceLocator.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-di/src/main/kotlin/questionsdi/QuestionsServiceLocator.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-di/src/main/kotlin/reviewsdi/ReviewsServiceLocator.kt)]) to be then implemented accordingly (see [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-di/src/main/kotlin/questionsdi/QuestionDependencies.kt)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/reviews/reviews-di/src/main/kotlin/reviewsdi/ReviewsDeps.kt)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/questions/questions-di/src/main/kotlin/questionsdi/QuestionDependencies.kt)]).

I really wanted to avoid indiscriminately instantiating references everywhere and I felt it was important to accommodate with mocking strategies for later testing, so this pattern would allow me keep that desired consistency.

I failed though to make [AppServiceLocator](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/blob/main/common/common-di/src/main/kotlin/commondi/AppServiceLocator.kt) a _singleton_ shared by all three main Chrome components. I introduced a bit of technical debt with this approach.

## Testing

As per my understanding, front-end testing is still a work-in-progress in Kotlin/JS framework. We don’t have access to the extensive set of Testing frameworks available for Java powered applications due to limitations of the JavaScript execution environment.

However, the Kotlin/JS Gradle plugin provides a [simple test library](https://kotlinlang.org/docs/js-running-tests.html) that is enough for at least simple assertion tests.

The Gradle wrapper allows to run tests in the browser via the **browserTest** task.

Test reports will be accessible from

_build/reports/tests/browserTest/index.html_

See [[1](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/item/item-data/src/test/kotlin/itemdata)][[2](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/item/item-domain/src/test/kotlin/itemdomain/interactors)][[3](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/item/item-html/src/test/kotlin/itemhtml)][[4](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/item/item-storage/src/test/kotlin/itemstorage)][[5](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-data/src/test/kotlin/questionsdata)][[6](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-domain/src/test/kotlin/questionsdomain)][[7](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-presentation/src/test/kotlin/questionspresentation)][[8](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-remote/src/test/kotlin/questionsremote)][[9](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/questions/questions-storage/src/test/kotlin/questionsstorage)][[10](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-data/src/test/kotlin/reviewsdata)][[11](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-domain/src/test/kotlin/reviewsdomain/helpers)][[12](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-presentation/src/test/kotlin/reviewspresentation)][[13](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-remote/src/test/kotlin/reviewsremote)][[14](https://github.com/lucas-dev/chrome-extension-clean-architecture-poc/tree/main/reviews/reviews-storage/src/test/kotlin/reviewsstorage)]

## Debugging

The browser provides a convenient extensions management page (which can be accessed via _chrome://extensions_) to visualize error logs, but when it comes to debugging with breakpoints we have to keep individual Chrome DevTools panels opened for each component script so I, for my case, actually found more straightforward to resort on console printing for flow debugging and then unit testing for program logic/code units.

## Deployment

The [Gradle Distribution Plugin](https://docs.gradle.org/current/userguide/distribution_plugin.html) provides a task to assemble and test the project. By running **gradlew installDist**, a copy of the extension that will be used for development purposes will be outputted at _build/install/[_project-name_],_ then from _chrome://extensions_ page, we load that generated folder as an _unpacked extension_.

Now, for the actual production deployment, we need to upload to the [Chrome Store](https://chrome.google.com/webstore) the zip file to be generated at _build/distributions_ after running the **distZip** task.

## Conclusion

I don’t know if this is a solid solution that can be adopted by most projects, but I personally feel comfortable with the end result when compared to the standard chrome extension architecture approach… This is easily testable, modularized, scalable… a few question marks staying around regarding some technical decisions, but one thing I know for sure is that because I followed a pattern I am familiar with I know that anytime in the future if I were to quickly look at the code then I will almost immediately have the big picture of what’s going on, like a solid idea of how to locate things and how to scale and that for me is a relief.

[Link to Medium article](https://medium.com/@lucas.abgodoy/chrome-extension-development-with-clean-architecture-a-poc-22e861aa4ede)