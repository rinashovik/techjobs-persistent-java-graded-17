--Part 1

--
--id int PK
--employer varchar(255)
--name varchar(255)
--skills varchar(255)
--


--Part 2
--select * from employer where location="St. Louis City";
SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3
DROP TABLE job;


--Part 4
--write a query to return the names of all skills that are attached to jobs in alphabetical order.
--If a skill does not have a job listed, it should not be included in the results of this query.
--SELECT all name FROM skill

-
SELECT * FROM skill LEFT JOIN job_skills ON skill.id = job_skills.skills_id WHERE job_skills.jobs_id IS NOT NULL ORDER BY name ASC;