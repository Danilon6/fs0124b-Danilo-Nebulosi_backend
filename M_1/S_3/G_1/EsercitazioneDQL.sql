
--Query 1

--SELECT *
--	FROM epicode."Clienti"
--		WHERE nome = 'Mario'

--Query 2
--SELECT nome, cognome
--	FROM epicode."Clienti"
--		WHERE anno_di_nascita=1982

--Query 3
--SELECT *
--	FROM epicode."Fatture"
--		WHERE iva=20

--Query 4
--SELECT *
--	FROM epicode."Prodotti"
--		WHERE extract (year from "data_attivazione") = 2017 AND (in_produzione=true OR in_commercio=true)

--Query 5
--SELECT *
--	FROM epicode."Clienti" c
--		JOIN epicode."Fatture" f
--			ON c.numero_cliente = f.id_cliente
--				WHERE f.importo < 1000

--Query 6
--SELECT numero_fattura, importo, iva, data_fattura, denominazione
--	FROM epicode."Fornitori" fo 
--		JOIN epicode."Fatture" fa
--			ON fa.numero_fornitore = fo.numero_fornitore
--SELECT COUNT(*), EXTRACT(YEAR FROM data_fattura) AS anno FROM fatture WHERE iva=20 GROUP BY anno

--Query 7
--SELECT extract(year from data_fattura) anno, count(*) count_fatture
--	FROM epicode."Fatture" f
--		WHERE f.iva=20 
--			GROUP BY anno

--Query 8
--SELECT extract(year from data_fattura) anno, COUNT(*) count_fatture, SUM(importo) totale
--	FROM epicode."Fatture" f
--		GROUP BY anno

--Query 9
--SELECT EXTRACT(YEAR FROM data_fattura) anno, COUNT(*) count_fatture
--	FROM epicode."Fatture"
--		WHERE tipologia = 'A'
--			GROUP BY anno
--				HAVING COUNT(*) >= 2;

--Query 10
--SELECT SUM(importo), regione_residenza
--	FROM epicode."Clienti" c
--		JOIN epicode."Fatture" f
--			ON c.numero_cliente = f.id_cliente
--				GROUP BY regione_residenza

--Query 11
--SELECT nome, cognome, COUNT(*)
--	FROM epicode."Clienti" c
--		JOIN epicode."Fatture" f
--			ON c.numero_cliente = f.id_cliente
--				WHERE anno_di_nascita = 1980 and f.importo >= 50.00
--					GROUP BY numero_cliente