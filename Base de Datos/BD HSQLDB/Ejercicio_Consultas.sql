
--Devuelve todas las películas
	SELECT * FROM movies ​​​​​​​

--Devuelve todos los géneros de las películas
	SELECT * FROM genre

--Devuelve el nombre de todas las películas y el nombre del estudio que las ha realizado
	SELECT m.MOVIE_NAME, s.STUDIO_NAME 
	FROM MOVIES m 
	LEFT JOIN STUDIO s ON m.STUDIO_ID = s.STUDIO_ID 

--Devuelve el nombre y la edad de todos los directores menores o iguales de 50 años
	SELECT * FROM 
		(SELECT d.DIRECTOR_NAME, 
			CASE d.DIRECTOR_DEAD_DATE
				WHEN IS NULL THEN 
					 DATEDIFF(YEAR,d.DIRECTOR_BIRTH_DATE,CURRENT_DATE) 
				ELSE 
					 DATEDIFF(YEAR,d.DIRECTOR_BIRTH_DATE,d.DIRECTOR_DEAD_DATE)   
				END 
				AS EDAD  
		FROM DIRECTORS d)
	WHERE EDAD <= 50   

--Devuelve el nombre y la edad de todos los actores menores de 50 años que hayan fallecido
	
	SELECT * FROM 
		(SELECT a.ACTOR_NAME, 
				DATEDIFF(YEAR,a.ACTOR_BIRTH_DATE,a.ACTOR_DEAD_DATE)    AS EDAD  
		FROM ACTORS a
		WHERE a.ACTOR_DEAD_DATE IS NOT NULL)
	WHERE EDAD <= 50

	
		SELECT a.ACTOR_NAME, 
				DATEADD(YEAR,50, a.ACTOR_BIRTH_DATE) AS cumple50  
		FROM ACTORS a
		WHERE a.ACTOR_DEAD_DATE IS NOT NULL
		AND DATEADD(YEAR,50, a.ACTOR_BIRTH_DATE) > a.ACTOR_DEAD_DATE
		
--Devuelve el nombre de todos los directores menores o iguales de 40 años que estén vivos
	SELECT * FROM 
		(SELECT d.DIRECTOR_NAME, 
				DATEDIFF(YEAR,d.DIRECTOR_BIRTH_DATE,CURRENT_DATE)    AS EDAD  
		FROM DIRECTORS d
		WHERE d.DIRECTOR_DEAD_DATE IS NULL)
	WHERE EDAD <= 40   

--Devuelve la lista de todos los estudios de grabación que estén activos
	--SELECT * FROM STUDIO s WHERE s.STUDIO_ID IN (SELECT m.STUDIO_ID FROM MOVIES m)
	SELECT * FROM STUDIO s WHERE s.STUDIO_ACTIVE = 1
	
--Devuelve el nombre y el año de todas las películas que han sido producidas por un estudio que actualmente no esté activo
	--A
	SELECT m.MOVIE_NAME, EXTRACT(YEAR FROM m.MOVIE_LAUNCH_DATE) AS YEAR 
		FROM MOVIES m
		WHERE m.STUDIO_ID IN (SELECT s.STUDIO_ID FROM STUDIO s WHERE s.STUDIO_ACTIVE = 0)
	
		--B		
	SELECT m.MOVIE_NAME, EXTRACT(YEAR FROM m.MOVIE_LAUNCH_DATE) AS YEAR
	FROM MOVIES m	
	JOIN STUDIO s ON s.STUDIO_ACTIVE = 0 AND m.STUDIO_ID = s.STUDIO_ID 
		
			
--Devuelve una lista de los 20 últimos miembros en anotarse al videoclub
	SELECT * FROM MEMBERS m 
	ORDER BY m.MEMBER_DISCHARGE_DATE DESC 
	LIMIT 20 
	
--Devuelve una lista de las últimas 10 películas que se han alquilado
	SELECT m.* 
	FROM MEMBERS_MOVIE_RENTAL mmr
	LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID 
	ORDER BY mmr.MEMBER_RENTAL_DATE DESC
	LIMIT 10
	
--Indica cual es el nombre del director del que más películas se han alquilado

	--A
	SELECT d.DIRECTOR_NAME FROM DIRECTORS d 
	WHERE 
		d.director_id =
		(	SELECT aux.DIRECTOR_ID
			FROM 	(	SELECT m.DIRECTOR_ID, count(m.DIRECTOR_ID) ALQUILADAS
						FROM MEMBERS_MOVIE_RENTAL mmr
						LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID		
						GROUP BY m.DIRECTOR_ID 
						ORDER BY ALQUILADAS DESC
						LIMIT 1
					) aux
		)
	
	--B
	SELECT d.DIRECTOR_NAME, count(m.DIRECTOR_ID) ALQUILADAS
	FROM MEMBERS_MOVIE_RENTAL mmr
		LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID		
		LEFT JOIN DIRECTORS d ON m.DIRECTOR_ID = d.DIRECTOR_ID 		
	GROUP BY d.DIRECTOR_NAME
	ORDER BY ALQUILADAS DESC
	LIMIT 1
	
	
	SELECT d.DIRECTOR_NAME, count(m.DIRECTOR_ID) ALQUILADAS
	FROM MEMBERS_MOVIE_RENTAL mmr
		LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID		
		LEFT JOIN DIRECTORS d ON m.DIRECTOR_ID = d.DIRECTOR_ID 		
	GROUP BY d.DIRECTOR_NAME
	ORDER BY ALQUILADAS DESC
	LIMIT 1
	
	
--Indica cuantos premios han ganado cada uno de los estudios con las películas que han creado

	
--Indica cuántas películas ha realizado cada director antes de cumplir 41 años
	SELECT d.DIRECTOR_NAME, count(d.DIRECTOR_NAME) AS cantidad 
	FROM MOVIES m
		LEFT JOIN DIRECTORS d ON m.DIRECTOR_ID  = d.DIRECTOR_ID
	WHERE DATEDIFF(YEAR,d.DIRECTOR_BIRTH_DATE,m.MOVIE_LAUNCH_DATE) < 41 
	GROUP BY d.DIRECTOR_NAME 

--Indica la edad media de los directores vivos
	SELECT avg(DATEDIFF(YEAR,d.DIRECTOR_BIRTH_DATE,CURRENT_DATE))
	FROM DIRECTORS d
	WHERE d.DIRECTOR_DEAD_DATE IS NULL 
	
--Indica la edad media de los actores que han fallecido
	SELECT avg(DATEDIFF(YEAR,a.ACTOR_BIRTH_DATE,a.ACTOR_DEAD_DATE))
	FROM ACTORS a
	WHERE a.ACTOR_DEAD_DATE IS NOT NULL
	
--(Roja) Indica cuál es el género favorito de cada uno de los directores cuando dirigen una película
	
	WITH cantidad_generos_directores AS (SELECT DIRECTOR_ID, GENRE_ID , count(GENRE_ID) AS CANTIDAD_GENEROS
										FROM MOVIES
										GROUP BY DIRECTOR_ID, GENRE_ID)
	SELECT d.DIRECTOR_NAME, GROUP_CONCAT(g.GENRE_NAME) AS GENRE_NAME 
	FROM (SELECT DIRECTOR_ID, max(CANTIDAD_GENEROS) AS MAXIMO
			FROM cantidad_generos_directores
			GROUP BY DIRECTOR_ID) AS aux
	LEFT JOIN cantidad_generos_directores cgd ON aux.DIRECTOR_ID = cgd.DIRECTOR_ID
												AND aux.MAXIMO = cgd.CANTIDAD_GENEROS
	LEFT JOIN GENRE g ON cgd.GENRE_ID = g.GENRE_ID 
	LEFT JOIN DIRECTORS d ON aux.DIRECTOR_ID = d.DIRECTOR_ID 
	GROUP BY d.DIRECTOR_NAME
	

	--dudas
	SELECT DIRECTOR_ID, max(CANTIDAD_GENEROS)
	FROM 
		(SELECT DIRECTOR_ID, GENRE_ID , count(GENRE_ID) AS CANTIDAD_GENEROS
		FROM MOVIES
		GROUP BY DIRECTOR_ID, GENRE_ID)
	GROUP BY DIRECTOR_ID 
		
	
--(Roja)Indica cuál es la nacionalidad favorita de cada uno de los estudios en la producción de las películas
	WITH repeticios_nacionalidad_estudio_pelicula AS (SELECT STUDIO_ID, NACIONALITY_ID, count(NACIONALITY_ID) AS REPETICIONES
														FROM MOVIES
														GROUP BY  STUDIO_ID, NACIONALITY_ID)
	SELECT s.STUDIO_NAME, GROUP_CONCAT(n.NACIONALITY_NAME) AS NACIONALITY_NAME
	FROM 
		(SELECT STUDIO_ID, MAX(REPETICIONES) AS MAXIMO
		FROM repeticios_nacionalidad_estudio_pelicula
		GROUP BY STUDIO_ID) AS aux
	LEFT JOIN repeticios_nacionalidad_estudio_pelicula rnep ON aux.STUDIO_ID = rnep.STUDIO_ID
															AND aux.MAXIMO = rnep.REPETICIONES				
	LEFT JOIN STUDIO s ON rnep.STUDIO_ID = s.STUDIO_ID
	LEFT JOIN NACIONALITY n ON rnep.NACIONALITY_ID = n.NACIONALITY_ID
	GROUP BY s.STUDIO_NAME 
												
--Indica cuál es la media de duración de las películas de cada director
SELECT d.DIRECTOR_NAME, AVG(m.MOVIE_DURATION) AS duracion_media 
	FROM MOVIES m
		LEFT JOIN DIRECTORS d ON m.DIRECTOR_ID  = d.DIRECTOR_ID
	GROUP BY d.DIRECTOR_NAME 
	
--Indica cuál es la el nombre y la duración mínima de las películas que han sido alquiladas en los últimos 2 años por los miembros del videoclub
	SELECT m.MOVIE_NAME, mmr.MEMBER_RENTAL_DATE 
	FROM MEMBERS_MOVIE_RENTAL mmr
		LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID 
	WHERE DATEDIFF(YEAR,mmr.MEMBER_RENTAL_DATE,CURRENT_DATE) <= 2 
	
	
	SELECT m.MOVIE_NAME, mmr.MEMBER_RENTAL_DATE 
	FROM MEMBERS_MOVIE_RENTAL mmr
		LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID 
	WHERE DATEADD(YEAR,-5,TODAY()) < mmr.MEMBER_RENTAL_DATE 
 
--(Roja)Indica cuál fue la primera película que alquilaron los miembros del videoclub cuyos teléfonos tengan como último dígito el ID de alguna nacionalidad

--respuesta: 							
SELECT lmf.MEMBER_NAME, m.MOVIE_NAME 
FROM MEMBERS_MOVIE_RENTAL mmr
JOIN (SELECT *
		FROM MEMBERS me 
		WHERE CAST(RIGHT(me.MEMBER_PHONE,1) AS INTEGER) IN (SELECT n.NACIONALITY_ID FROM NACIONALITY n)
		) lmf 
	ON mmr.MEMBER_ID = lmf.MEMBER_ID
JOIN (SELECT MEMBER_ID, min(MEMBER_RENTAL_DATE) FECHA
		FROM  MEMBERS_MOVIE_RENTAL 
		GROUP BY MEMBER_ID
		) lpp
	ON lpp.MEMBER_id = mmr.MEMBER_ID 
	AND lpp.FECHA = mmr.MEMBER_RENTAL_DATE 
LEFT JOIN MOVIES m	ON m.MOVIE_ID = mmr.MOVIE_ID 
ORDER BY MEMBER_NAME



--como filtrar esto:
SELECT me.MEMBER_NAME, min(mmr.MEMBER_RENTAL_DATE) PRIMERA, m.MOVIE_NAME
	FROM MEMBERS_MOVIE_RENTAL mmr
		LEFT JOIN MOVIES m ON mmr.MOVIE_ID = m.MOVIE_ID
		LEFT JOIN MEMBERS me ON mmr.MEMBER_ID = me.MEMBER_ID 
	WHERE CAST(RIGHT(me.MEMBER_PHONE,1) AS INTEGER) IN (SELECT n.NACIONALITY_ID FROM NACIONALITY n)
	GROUP BY me.MEMBER_NAME, m.MOVIE_NAME
	ORDER BY me.MEMBER_NAME 

	
	
--Indica el número de premios a los que estuvo nominado un actor, pero que no ha conseguido (Si una película está nominada a un premio, su actor también lo está)

	
--Indica cuantos actores y directores hicieron películas para los estudios no activos
	
	SELECT count(DISTINCT m.DIRECTOR_ID) AS DIRECTORES, count(DISTINCT ma.ACTOR_ID) AS ACTORES
	FROM MOVIES m
	RIGHT JOIN STUDIO s ON m.STUDIO_ID = s.STUDIO_ID AND s.STUDIO_ACTIVE = 0
	LEFT JOIN MOVIES_ACTORS ma ON m.MOVIE_ID = ma.MOVIE_ID 
	
--Indica el nombre, ciudad, y teléfono de todos los miembros del videoclub que hayan alquilado películas que hayan sido nominadas a más de 150 premios y ganaran menos de 50

--Indica el número de películas que hayan hecho los directores durante las décadas de los 60, 70 y 80 que contengan la palabra "The" en cualquier parte del título
	SELECT d.DIRECTOR_NAME, COUNT(m.MOVIE_ID) 
	FROM MOVIES m
	LEFT JOIN DIRECTORS d ON m.DIRECTOR_ID  = d.DIRECTOR_ID 
	WHERE m.MOVIE_NAME LIKE '%The%'
			AND EXTRACT(YEAR FROM m.MOVIE_LAUNCH_DATE) >= 1960 
			AND EXTRACT(YEAR FROM m.MOVIE_LAUNCH_DATE) < 1990
	GROUP BY d.DIRECTOR_NAME
	
--Indica si hay alguna coincidencia de nacimiento de ciudad (y si las hay, indicarlas) entre los miembros del videoclub y los directores.
	SELECT * 
	FROM DIRECTORS d
	INNER JOIN MEMBERS me ON d.DIRECTOR_BIRTH_PLACE = me.MEMBER_TOWN

--Comprueba si hay errores en la BD entre las películas y directores (un director muerto en el 76 no puede dirigir una película en el 88)
	SELECT m.MOVIE_NAME, m.MOVIE_LAUNCH_DATE, d.DIRECTOR_NAME, d.DIRECTOR_DEAD_DATE 
	FROM MOVIES m 
	INNER JOIN DIRECTORS d ON d.DIRECTOR_ID  = m.DIRECTOR_ID 
							AND d.DIRECTOR_DEAD_DATE < m.MOVIE_LAUNCH_DATE  


--Usando como condición la sentencia anterior, modifica la fecha de defunción a un año más tarde del estreno de la película (mediante sentencia SQL)

	WITH AUX AS (SELECT d.DIRECTOR_ID, m.MOVIE_NAME, m.MOVIE_LAUNCH_DATE, d.DIRECTOR_NAME, d.DIRECTOR_DEAD_DATE, DATEADD(YEAR, 1, m.MOVIE_LAUNCH_DATE) AS DIRECTOR_DEAD_DATE_UPDATE
				FROM MOVIES m 
				INNER JOIN DIRECTORS d ON d.DIRECTOR_ID  = m.DIRECTOR_ID 
				AND d.DIRECTOR_DEAD_DATE < m.MOVIE_LAUNCH_DATE)									
	UPDATE DIRECTORS d 
		INNER JOIN AUX a ON  d.DIRECTOR_ID = a.DIRECTOR_ID
	SET d.DIRECTOR_DEAD_DATE = a.DIRECTOR_DEAD_DATE_UPDATE
	WHERE d.DIRECTOR_ID = a.DIRECTOR_ID

	
	
	WITH AUX AS (SELECT d.DIRECTOR_ID, m.MOVIE_NAME, m.MOVIE_LAUNCH_DATE, d.DIRECTOR_NAME, d.DIRECTOR_DEAD_DATE, DATEADD(YEAR, 1, m.MOVIE_LAUNCH_DATE) AS DIRECTOR_DEAD_DATE_UPDATE
				FROM MOVIES m 
				INNER JOIN DIRECTORS d ON d.DIRECTOR_ID  = m.DIRECTOR_ID 
				AND d.DIRECTOR_DEAD_DATE < m.MOVIE_LAUNCH_DATE)									
	UPDATE DIRECTORS d, AUX a
	SET d.DIRECTOR_DEAD_DATE = a.DIRECTOR_DEAD_DATE_UPDATE
	WHERE d.DIRECTOR_ID = a.DIRECTOR_ID
		
	
	
	UPDATE DIRECTORS d2
	SET d2.DIRECTOR_DEAD_DATE = (SELECT DATEADD(YEAR, 1, m.MOVIE_LAUNCH_DATE)
									FROM MOVIES m 
										INNER JOIN DIRECTORS d ON d.DIRECTOR_ID  = m.DIRECTOR_ID 
										AND d.DIRECTOR_DEAD_DATE < m.MOVIE_LAUNCH_DATE
									WHERE d.DIRECTOR_ID = d2.DIRECTOR_ID
									LIMIT 1)	
	WHERE d2.DIRECTOR_ID IN (SELECT d.DIRECTOR_ID
								FROM MOVIES m 
								INNER JOIN DIRECTORS d ON d.DIRECTOR_ID  = m.DIRECTOR_ID 
								AND d.DIRECTOR_DEAD_DATE < m.MOVIE_LAUNCH_DATE)	
--- coreccion 1
	   MERGE INTO
      PUBLIC.DIRECTORS D
          USING (
      SELECT
          D.DIRECTOR_ID,
          MAX(DATEADD(YEAR, 1, M.MOVIE_LAUNCH_DATE)) AS DIRECTOR_DEAD_DATE
      FROM
          PUBLIC.MOVIES M
      INNER JOIN PUBLIC.DIRECTORS D ON
          M.DIRECTOR_ID = D.DIRECTOR_ID
      WHERE
          D.DIRECTOR_DEAD_DATE IS NOT NULL
          AND D.DIRECTOR_DEAD_DATE < M.MOVIE_LAUNCH_DATE
      GROUP BY
          D.DIRECTOR_ID) AS L(DIRECTOR_ID,
      DIRECTOR_DEAD_DATE) ON
      D.DIRECTOR_ID = L.DIRECTOR_ID
      WHEN MATCHED THEN UPDATE
      SET
          D.DIRECTOR_DEAD_DATE = L.DIRECTOR_DEAD_DATE	
          
--- correcion??? 2
 UPDATE DIRECTORS SET  DIRECTOR_DEAD_DATE = (SELECT  MAX(M.MOVIE_LAUNCH_DATE) AS LAUNCH_DATE FROM MOVIES M 
											 WHERE M.DIRECTOR_ID = DIRECTOR_ID 
											 GROUP BY DIRECTOR_ID ORDER BY DIRECTOR_ID LIMIT 1)
 WHERE DIRECTOR_ID IN ( SELECT DISTINCT DIRECTOR_ID
						  FROM
						          MOVIES M
						      INNER JOIN DIRECTORS D ON
						          M.DIRECTOR_ID = D.DIRECTOR_ID
						       WHERE         D.DIRECTOR_DEAD_DATE IS NOT NULL
						          AND D.DIRECTOR_DEAD_DATE < M.MOVIE_LAUNCH_DATE)          