package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TEAMSProcessor {

    private Collection<People> _allpeople = null;
    private String _fileName;
    private String _libelle;
    private String _startTime;
    private String _endTime;
    private Tris _tris;

    public TEAMSProcessor(File _file, String _start, String _stop,Tris _t,String lib) {
        /*
         csv file to read
         start time of the course
         end time of the source
        */
        this._startTime = _start;
        this._endTime = _stop;

        // load CSV file
        this._fileName = _file.getName();
        var teamsFile = new TEAMSAttendanceList(_file);
        
        //Sort people
        this._tris=_t;
        
        //Lesson Title
        this._libelle = lib;
        
        // filter to extract data for each people
        var lines = teamsFile.get_attlist();
        if (lines != null) {
            // convert lines in data structure with people & periods
            var filter = new TEAMSAttendanceListAnalyzer(lines);
            // cut periods before start time and after end time
            filter.setStartAndStop(_start, _stop);
            // sort
            Collection<People> peopleByDuration = new ArrayList<>(filter.get_peopleList().values());
            //Collections.sort(peopleByDuration);
            // init the people collection
           
            this._allpeople = peopleByDuration;//filter.get_peopleList().values();
        }
    }

    public Collection<People> get_allpeople() {
        return _allpeople;
    }
    
    public void sort() {
        List<People> list = (List<People>) this._allpeople;
        _tris.sort(list);
        this._allpeople = list;
    }
    
    public void setSorter(Tris tris) {
        this._tris = tris;
    }
    public String toHTMLCode() {

        String html = "<!DOCTYPE html> \n <html lang=\"fr\"> \n <head> \n <meta charset=\"utf-8\"> ";
        html += "<title> Attendance Report </title> \n <link rel=\"stylesheet\" media=\"all\" href=\"visu.css\"> \n";
        html += "</head> \n <body> \n ";
        html += "<h1> Rapport de connexion </h1>\n" +
                "\n" +
                "<div id=\"blockid\">\n" +
                "<table>\n" +
                "\t<tr>\n" +
                "\t\t<th> Date : </th>\n" +
                "\t\t<td> " + /*this._allpeople.iterator().next().getDate() +*/ " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Heure d�but : </th>\n" +
                "\t\t<td> " + this._startTime + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Heure fin : </th>\n" +
                "\t\t<td> " + this._endTime + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Cours : </th>\n" +
                "\t\t<td> " + this._libelle + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Fichier analys� : </th>\n" +
                "\t\t<td> " + this._fileName + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Nombre de connect�s : </th>\n" +
                "\t\t<td> " + this._allpeople.size() + "  </td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "\n" +
                "<h2> Dur�es de connexion</h2>\n" +
                "\n" +
                "<p> Pour chaque personne ci-dessous, on retrouve son temps total de connexion sur la plage d�clar�e du cours, ainsi qu'un graphe qui indique les p�riodes de connexion (en vert) et d'absence de connexion (en blanc). En pointant la souris sur une zone, une bulle affiche les instants de d�but et de fin de p�riode. \n" +
                "</p>";
        html += "<div id=\"blockpeople\"> ";

        for (People people : this._allpeople) {

            html += people.getHTMLCode();
        }

	    html += "</div> \n </body> \n </html>";
        return html;
    }
}
