<?php
/*
 * © Copyright IBM Corp. 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

/**
 * Files view widget.
 *
 * @author Benjamin Jakobus
 */
class SBTFilesViewWidget extends SBTBaseGridWidget {
	
	private $widget_id = 'ibm_sbtk_files_view_widget';
	private $widget_name = 'IBM Files View';
	private $widget_description = 'Use this widget to add an interactive files grid, allowing you to display and edit files from IBM SmartCloud for Social Business or IBM Connections on Premise.';
	private $widget_location =  '/views/widgets/ibm-sbt-files-view.php';

	/**
	 * Constructor.
	 */
	public function __construct() {
		parent::__construct($this->widget_id, $this->widget_name, $this->widget_description, BASE_PATH . $this->widget_location);
	}
	
	/**
	 * Ouputs the options form on admin
	 *
	 * @param array $instance The widget options
	 */
	public function form($instance) {
		parent::form($instance);
		
		if (isset($instance['ibm-sbtk-files-type'])) {
			$type = $instance['ibm-sbtk-files-type'];
		} else {
			$type = "publicFiles";
		}
		
		if (isset($instance['ibm-sbtk-files-action-bar'])) {
			$actionBar = $instance['ibm-sbtk-files-action-bar'];
		} else {
			$actionBar = true;
		}
		?>
		<p>
			<label for="<?php echo $this->get_field_id('ibm-sbtk-files-action-bar'); ?>"><?php echo $GLOBALS[LANG]['action-bar']?>:</label> 
			<input <?php echo ($actionBar ? 'checked="checked"' : ''); ?> id="<?php echo $this->get_field_id('ibm-sbtk-files-action-bar'); ?>" name="<?php echo $this->get_field_name('ibm-sbtk-files-action-bar'); ?>" type="checkbox" value="actionBar" />
		</p>
		<p>
			<label for="<?php echo $this->get_field_id('ibm-sbtk-files-type'); ?>"><?php echo $GLOBALS[LANG]['file-type']?>:</label> 
			<select id="<?php echo $this->get_field_id('ibm-sbtk-files-type'); ?>" name="<?php echo $this->get_field_name('ibm-sbtk-files-type'); ?>">
				<option <?php echo ($type == 'myFiles' ? 'selected="selected"' : ''); ?> value="myFiles"><?php echo $GLOBALS[LANG]['my-files']?></option>
				<option <?php echo ($type == 'publicFiles' ? 'selected="selected"' : ''); ?> value="publicFiles"><?php echo $GLOBALS[LANG]['public-files']?></option>
				<option <?php echo ($type == 'myPinnedFiles' ? 'selected="selected"' : ''); ?> value="myPinnedFiles"><?php echo $GLOBALS[LANG]['pinned-files']?></option>
				<option <?php echo ($type == 'myFolders' ? 'selected="selected"' : ''); ?> value="myFolders"><?php echo $GLOBALS[LANG]['my-folders']?></option>
				<option <?php echo ($type == 'publicFolders' ? 'selected="selected"' : ''); ?> value="publicFolders"><?php echo $GLOBALS[LANG]['public-folders']?></option>
				<option <?php echo ($type == 'myPinnedFolders' ? 'selected="selected"' : ''); ?> value="myPinnedFolders"><?php echo $GLOBALS[LANG]['my-pinned-folders']?></option>
				<option <?php echo ($type == 'activeFolders' ? 'selected="selected"' : ''); ?> value="activeFolders"><?php echo $GLOBALS[LANG]['active-folders']?></option>
				<option <?php echo ($type == 'fileShares' ? 'selected="selected"' : ''); ?> value="fileShares"><?php echo $GLOBALS[LANG]['file-shares']?></option>
				<option <?php echo ($type == 'communityFiles' ? 'selected="selected"' : ''); ?> value="communityFiles"><?php echo $GLOBALS[LANG]['community-files']?></option>
			</select>
		</p>
		<?php 
	}
	
	/**
	 * Processing widget options on save
	 *
	 * @param array $new_instance The new options
	 * @param array $old_instance The previous options
	 */
	public function update($new_instance, $old_instance) {
		$instance = parent::update($new_instance, $old_instance);
		$instance['ibm-sbtk-files-action-bar'] = (!empty($new_instance['ibm-sbtk-files-action-bar'])) ? strip_tags($new_instance['ibm-sbtk-files-action-bar'] ) : '';
		$instance['ibm-sbtk-files-type'] = (!empty($new_instance['ibm-sbtk-files-type'])) ? strip_tags($new_instance['ibm-sbtk-files-type'] ) : '';
		return $instance;
	}
	
	/**
	 * Outputs the content of the widget.
	 *
	 * @param array $args
	 * @param array $instance
	 */
	public function widget($args, $instance) {
		parent::widget($args, $instance);
	}

}
