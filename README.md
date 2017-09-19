## photoRenamer
# School project for course CSC207 Software design, worked with another teammate Marshall Zhao.

The purpose of this project is to design a software that helps users view, rename and manage their local pictures(eg. JPEG files) by adding personalized (textual) tags to them.  


The user can choose a directory and view a list of all image files anywhere under that directory. When viewing an image, the user can select tags from the currently-existing tags, and the user can also add new tags and delete existing ones from the currently-existing ones. The application will rename the image file to include the tags, each prefixed with the "@" character. 


Provided that an image has not been moved to a different location or manually renamed using the OS, users can view all the names that a file has had. The system keeps a log of all renaming ever done (old name, new name, and timestamp), and the user can view this log.


When PhotoRenamer is first run, it should create any configuration files that it needs, and if they are deleted it should recreate them the next time it is run.
