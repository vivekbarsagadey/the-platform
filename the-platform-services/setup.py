
import io
import os
import re
import sys

from setuptools import setup, find_packages


RE_REQUIREMENT = re.compile(r'^\s*-r\s*(?P<filename>.*)$')

PYPI_RST_FILTERS = (
    # Replace Python crossreferences by simple monospace
    (r':(?:class|func|meth|mod|attr|obj|exc|data|const):`~(?:\w+\.)*(\w+)`', r'``\1``'),
    (r':(?:class|func|meth|mod|attr|obj|exc|data|const):`([^`]+)`', r'``\1``'),
    # replace doc references
    (r':doc:`(.+) <(.*)>`', r'`\1 <http://flask-restplus.readthedocs.org/en/stable\2.html>`_'),
    # replace issues references
    (r':issue:`(.+?)`', r'`#\1 <https://github.com/noirbizarre/flask-restplus/issues/\1>`_'),
    # Drop unrecognized currentmodule
    (r'\.\. currentmodule:: .*', ''),
)

def rst(filename):
    '''
    Load rst file and sanitize it for PyPI.
    Remove unsupported github tags:
     - code-block directive
     - all badges
    '''
    content = io.open(filename).read()
    for regex, replacement in PYPI_RST_FILTERS:
        content = re.sub(regex, replacement, content)
    return content

def pip(filename):
    '''Parse pip reqs file and transform it to setuptools requirements.'''
    requirements = []
    for line in io.open(os.path.join('requirements', '{0}.pip'.format(filename))):
        line = line.strip()
        if not line or '://' in line or line.startswith('#'):
            continue
        requirements.append(line)
    return requirements

long_description = '\n'.join((
    rst('README.rst'),
    rst('CHANGELOG.rst'),
    ''
))

install_requires = pip('install')

setup(
    name="the-platform-services",
    version="0.0.1",
    author="Vivek Barsagadey",
    author_email="vivek.bnb@gmail.com",
    description="Medical and Financial application based on machine learning",
    long_description=long_description,
    url="https://github.com/",
    include_package_data=True,
    install_requires=install_requires,
    package_dir={'the-platform-services': 'src'},
    packages=find_packages(exclude=['doc','tests']),
    classifiers=(
        "Programming Language :: Python :: 3.6",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ),
)