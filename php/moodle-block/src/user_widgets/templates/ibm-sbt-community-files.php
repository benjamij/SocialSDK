
Select your community:
<select id="ibm-sbt-communities-<?php echo $timestamp; ?>" onchange="onCommunityChange<?php echo $timestamp; ?>();"></select>
<br />
<div id="ibm-sbt-community-files-list-<?php echo $timestamp; ?>"></div>
<input type="file" id="ibm-sbt-community-files-<?php echo $timestamp; ?>" accept="image/*"></input>
<br/>
<button class="btn btn-primary" id="uploadBtn">Upload</button>
<br/>
<div id="ibm-sbt-community-files-error-<?php echo $timestamp; ?>" style="display: none;" class="alert alert-error"></div>
<img id="ibm-sbt-loading-<?php echo $timestamp; ?>" style="display: none;" src="<?php echo $CFG->wwwroot; ?>/blocks/ibmsbt/user_widgets/templates/assets/loading_small.gif" />
<span id="ibm-sbt-community-files-success-<?php echo $timestamp; ?>" class="alert alert-success"></span><br/>
<br/>

<script type="text/template" id="fileRow-<?php echo $timestamp; ?>">
<tr style="padding-bottom: 0.3em;">
	<td style="width:160px;  white-space: nowrap; overflow: hidden; text-overflow: ellipsis; display: inline-block; padding-left: 10px;">
			<span dojoAttachPoint="placeLinkNode">
				<a href="javascript: void(0)" target="_self" title="${title}" dojoAttachPoint="placeTitleLink" data-dojo-attach-event="onclick: handleClick">${title}</a>
			</span>
	</td>
</tr>
</script>
<script type="text/template" id="pagingHeader-<?php echo $timestamp; ?>">
<div dojoAttachPoint="pagingHeader">
	<div>
		<hr style="width:90%; left: -30px; border: 0; height: 1px;"/>
	</div>
	<span dojoAttachPoint="showingResultsMessage" style="font-size: 12px;">${pagingResults}</span>
			<span style="padding-left: 10px; font-size: 12px;">
				<a style="${hidePreviousLink};" title="${nls.previousPage}" href="javascript: void(0)" data-dojo-attach-event="onclick: prevPage">${nls.previous}</a>
				<span style="${hidePreviousLabel}">${nls.previous}</span>
			</span>

			<a style="${hideNextLink} align: right; font-size: 12px;" title="${nls.nextPage}" href="javascript: void(0)" data-dojo-attach-event="onclick: nextPage">${nls.next}</a>
			<span style="${hideNextLabel}">${nls.next}</span>
		<div>
			<hr style="width:90%; background: black; margin: 0.1em 0; left: -30px; border: 0; height: 1px;"/>
		</div>
</div>
</script>
<script type="text/template" id="pagingFooter-<?php echo $timestamp; ?>">
<div dojoattachpoint="pagingFooter" class="lotusPaging" style="font-size: 12px;">
	<div>
		<hr style="width:90%; background: black;  margin: 0.1em 0; left: -30px; border: 0; height: 1px;"/>
	</div>
		Show:
			<a href="javascript: void(0)" title="${nls.show10Items}" aria-pressed="false"
				role="button" data-dojo-attach-event="onclick: show10ItemsPerPage">10</a> |

			<a href="javascript: void(0)"
				title="${nls.show25Items}" data-dojo-attach-event="onclick: show25ItemsPerPage"
				aria-pressed="false" role="button">25</a> |
		
			<a href="javascript: void(0)" title="${nls.show50Items}" data-dojo-attach-event="onclick: show50ItemsPerPage"
			aria-pressed="false" role="button">50</a> |
	
	
			<a href="javascript: void(0)" title="${nls.show100Items}" data-dojo-attach-event="onclick: show100ItemsPerPage"
			aria-pressed="false" role="button">100</a>
		 ${nls.items}
	<div>
		<hr style="width:90%; margin: 0.7em 0; left: -30px; border: 0; height: 1px;"/>
	</div>
</div>
</script>