## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar);`
* 'ALTER SEQUENCE animals_id_seq RESTART 10000000;'
* 'CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, lastseen timestamp);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

### License

Copyright (c) 2017 **_MIT License_**

## Changes Made By Joel

* 1. Added "required" inline html tags to all text form entry fields to prevent null entries to the database.
* 2. Corrected build.gradle archivebasename to java-wildlife-tracker.
* 3. Change formatting in animal.vtl and endangeredanimal.vtl to enhance readability  of each animal sighting.
* 4. Updated the all method in endangeredanimal.java to troubleshoot all method conflict with animal class.
* 5. Added ability for date/time of each sighting to be recorded to the database and shown on the list of sightings.
