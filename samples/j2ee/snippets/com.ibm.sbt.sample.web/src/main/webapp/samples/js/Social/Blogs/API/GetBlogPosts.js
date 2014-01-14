require(["sbt/connections/BlogService", "sbt/dom", "sbt/json"], 	
    function(BlogService, dom, json) {
        var blogService = new BlogService(); 
        var now = new Date();
        var post = blogService.newBlogPost();
        post.setTitle("BlogPost at " + now.getTime());
        post.setContent("BlogPost Content at " + now.getTime());
        var comment = blogService.newComment();
        comment.setContent("Comment Content at " + now.getTime());
        
    	blogService.getAllBlogs({ ps: 1 }).then(
			function(blogs){
				post.setBlogHandle(blogs[0].getHandle());
				return blogService.createPost(post); // returning newly created blog post
            }
    	).then(
			function(createdPost){
				return blogService.getBlogPosts(createdPost.getBlogHandle());
            }
    	).then(
    		function(posts) {
                dom.setText("json", json.jsonBeanStringify(posts));
            },
            function(error) {
                dom.setText("json", json.jsonBeanStringify(error));
            }
        );
	}
);