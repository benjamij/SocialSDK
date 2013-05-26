require(["sbt/dom", "sbt/json", "sbt/smartcloud/ProfileService"], function(dom,json,ProfileService) {
    try {
    	var results = "";
        var profileService = new ProfileService();
        var promise = profileService.getMyContacts();
        promise.then(    
            function(profiles){
            	dom.setText("json", json.jsonBeanStringify(getResults(profiles)));
            },
            function(error){            	
                dom.setText("json", json.jsonBeanStringify(error));
            }
        );
    } catch(error) {
        dom.setText("json", json.jsonBeanStringify(error));
    }
});
function getResults(profiles) {
	var results = []; 
	for (var i=0;i<profiles.length;i++) {
		results.push(profiles[i].getData()); 
	}
    return  results;
};