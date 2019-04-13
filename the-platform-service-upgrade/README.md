The Platform Package
-------

This is a simple example package. You can use
[Github-The-Platform-Services](https://github.com/vivekbarsagadey/the-platform.git)
to write your content.

Install
-------
## clone the repository
    git clone https://github.com/vivekbarsagadey/the-platform.git
    cd the-platform-service-upgrade
    # checkout the correct version
    git tag  # shows the tagged versions
    git checkout latest-tag-found-above
    
Create a virtualenv in the the-platform-service-upgrade directory and activate it::

    python -m venv venv
    venv\Scripts\activate.bat
    
Install Dependencies in Virtual Environment::

    pip install -r requirements.txt
    
 RUN
 ---
 
 On Virtual Environment::
    
    flask run
    
Open http://127.0.0.1:5000 in a browser.