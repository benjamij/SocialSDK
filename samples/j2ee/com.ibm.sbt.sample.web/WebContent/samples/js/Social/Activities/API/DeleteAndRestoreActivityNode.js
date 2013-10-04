require([ "sbt/dom", "sbt/json", "sbt/connections/ActivityService" ], function(dom, json, ActivityService) {
	var activitiesService = new ActivityService();
	var activityNodeId = "%{name=sample.activityNodeId|helpSnippetId=Social_Activities_API_GetActivityNode}";
	activitiesService.deleteActivityNode(activityNodeId).then(function(response) {
		return response;
	}, function(error) {
		dom.setText("json", json.jsonBeanStringify(error));
	}).then(function(response) {
		var promise = activitiesService.restoreActivityNode(activityNodeId);
		promise.then(function(response) {
			dom.setText("json", json.jsonBeanStringify(response));
		}, function(error) {
			dom.setText("json", json.jsonBeanStringify(error));
		});
	});
});
