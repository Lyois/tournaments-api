package com.example.tournaments.api.config;


import com.example.tournaments.api.players.PlayersRequestDTO;
import com.example.tournaments.api.teams.Teams;
import com.example.tournaments.api.teams.TeamsRequestDTO;
import com.example.tournaments.api.tournaments.TournamentsRequestDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Scraping {

    public List<String> listTournaments() {
        List<String> listTournaments = new ArrayList<>();
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/LCS%20Spring%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/CBLOL%20Split%201%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/LEC%20Winter%20Season%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/LCK%20Spring%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/LPL%20Spring%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/PCS%20Spring%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/VCS%20Spring%202024/");
        listTournaments.add("https://gol.gg/tournament/tournament-ranking/LLA%20Opening%202024/");
        return listTournaments;
    }

    public List<TeamsRequestDTO> scrapingTeamsData(String url) {
//        String url = "https://gol.gg/teams/team-stats/2416/split-ALL/tournament-NACL%20Spring%202024/";
        List<TeamsRequestDTO> listTeamsData = new ArrayList<TeamsRequestDTO>();
        List<String> list = new ArrayList<String>();
        try {
            var doc = Jsoup
                    .connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();

            var bodyTable = doc.selectXpath("/html/body/div/main/div[2]/div/div[3]/div/div[1]/div[1]/table/tbody/tr");

            var textTag = doc
                    .selectXpath("/html/body/div/main/div[2]/div/div[3]/div/div[2]/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[1]");

            var camp = doc.selectXpath("/html/body/div/main/div[2]/div/div[1]/div/div/div[2]/div/span[2]/select/option[2]").text().split(" ");

            var iconUrl = doc.selectXpath("/html/body/div/main/div[2]/div/div[3]/div/div[1]/div[2]/div/figure/img").attr("src").substring(2);

            var title = doc.getElementsByTag("title").text().toLowerCase();
            var tag = textTag.text().split(" ");

            for (Element element : bodyTable) {
                list.add(element.selectXpath("td[2]").text());
            }

            var name = doc.getElementsByTag("h1").text();
            var finalIconUrl = "http://gol.gg" + iconUrl;
            var region = list.getFirst();
            var season = list.get(1);
            var winsLoses = list.get(2).split(" ");
            var wins = Integer.parseInt(winsLoses[0].replace("W", ""));
            var loses = Integer.parseInt(winsLoses[2].replace("L", ""));
            var winPercent = Double.parseDouble(list.get(3).replace("%", ""));
            var gameDuration = Double.parseDouble(list.get(4).replace(":", "."));

            String split = title.contains("spring") || title.contains("split 1")
                    ? "SPRING"
                    : title.contains("summer") || title.contains("split 2")
                    ? "SUMMER"
                    : "ALL";

            String finalTag = (tag[0] + season + split);

            listTeamsData.add(new TeamsRequestDTO(camp[0], finalTag, tag[0], name, region, wins, loses, winPercent, gameDuration, finalIconUrl ));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listTeamsData;
    }

    public List<TournamentsRequestDTO> scrapingTournamentsData(String url) {

        List<TournamentsRequestDTO> listTournaments = new ArrayList<>();

        try {
            var doc = Jsoup
                    .connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();

            var data = doc.selectXpath("/html/body/div/main/div[2]/div/div[1]/div/nav/span").text().split(" ");
            var name = doc.selectXpath("/html/body/div/main/div[2]/div/h1[1]").text();
            var iconUrl = doc.selectXpath("/html/body/div/main/div[2]/div/h1[1]/img").attr("src").substring(2);
            var finalIconUrl = "http://gol.gg" + iconUrl;
            var tag = data[0];
            var season = data[1];

            listTournaments.add(new TournamentsRequestDTO(tag, name, season, finalIconUrl));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return listTournaments;
    }

    public List<String> scrapingTeams(String url) {
        List<String> listTeams = new ArrayList<>();

        try {
            var doc = Jsoup
                    .connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();

            var table = doc.selectXpath("/html/body/div/main/div[2]/div/div[3]/div/section/div/div/table/tbody/tr");

            for (Element lines : table) {
                var http = "http://gol.gg";
                var ahref = lines.selectXpath("td[1]/a").attr("href").replace(".", "");
                var urlFinal = http + ahref;
                listTeams.add(urlFinal);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return listTeams;
    }

    public List<PlayersRequestDTO> scrapingPlayers(String url) {

        List<PlayersRequestDTO> listPlayers = new ArrayList<>();

        try {
            String season;
            String split;
            String finalTag;

            var doc = Jsoup
                    .connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post();

            Elements tablePlayers = doc
                    .selectXpath("/html/body/div/main/div[2]/div/div[3]/div/div[6]/div/table/tbody/tr");

            var textTag = doc
                    .selectXpath("/html/body/div/main/div[2]/div/div[3]/div/div[2]/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[1]");

            season = doc.selectXpath("/html/body/div/main/div[2]/div/div[3]/div/div[1]/div[1]/table/tbody/tr[2]/td[2]").text();

            var title = doc.getElementsByTag("title").text().toLowerCase();
            var tag = textTag.text().split(" ");

            split = title.contains("spring") || title.contains("split 1")
                    ? "SPRING"
                    : title.contains("summer") || title.contains("split 2")
                    ? "SUMMER"
                    : "ALL";

            finalTag = (tag[0] + season + split);

            for (Element elem : tablePlayers) {

                if (elem.text().equalsIgnoreCase("Last line-up")) {
                    continue;
                } else if (elem.text().equalsIgnoreCase("Subs")) {
                    break;
                }

                String lane = (elem.selectXpath("td[1]").text());
                lane = lane.substring(0, 1).concat(lane.substring(1).toLowerCase());
                String name = (elem.selectXpath("td[2]").text());
                double kda = Double.parseDouble(elem.selectXpath("td[3]").text().replace("-", "0"));
                double kp = Double.parseDouble(elem.selectXpath("td[4]").text().replace("%", "").replace("-", "0"));
                double vspm = Double.parseDouble(elem.selectXpath("td[5]").text().replace("%", "").replace("-", "0"));
                double dmg = Double.parseDouble(elem.selectXpath("td[6]").text().replace("%", "").replace("-", "0"));
                double gold = Double.parseDouble(elem.selectXpath("td[7]").text().replace("%", "").replace("-", "0"));

                listPlayers.add(new PlayersRequestDTO(finalTag, lane, name, kda, kp, vspm, dmg, gold));
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listPlayers;
    }

    public Document connectUrl(String url) {
        return null;
    }
}
