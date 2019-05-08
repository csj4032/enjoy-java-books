package com.genius.jooq;

import java.util.Map;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.teeing;

public class JOOλExample {

    private static final String MVPS = "" +
            "2017-18     James Harden, Houston Rockets (Voting)\n" +
            "2016-17     Russell Westbrook, Oklahoma City Thunder (Voting)\n" +
            "2015-16     Stephen Curry, Golden State Warriors (Voting)\n" +
            "2014-15     Stephen Curry, Golden State Warriors\n" +
            "2013-14     Kevin Durant, Oklahoma City Thunder\n" +
            "2012-13     LeBron James, Miami Heat\n" +
            "2011-12     LeBron James, Miami Heat\n" +
            "2010-11     Derrick Rose, Chicago Bulls\n" +
            "2009-10     LeBron James, Cleveland Cavaliers\n" +
            "2008-09     LeBron James, Cleveland Cavaliers\n" +
            "2007-08     Kobe Bryant, Los Angeles Lakers\n" +
            "2006-07     Dirk Nowitzki, Dallas Mavericks\n" +
            "2005-06     Steve Nash, Phoenix Suns\n" +
            "2004-05     Steve Nash, Phoenix Suns\n" +
            "2003-04     Kevin Garnett, Minnesota Timberwolves\n" +
            "2002-03     Tim Duncan, San Antonio Spurs\n" +
            "2001-02     Tim Duncan, San Antonio Spurs\n" +
            "2000-01     Allen Iverson, Philadelphia 76ers\n" +
            "1999-00     Shaquille O'Neal, Los Angeles Lakers\n" +
            "1998-99     Karl Malone, Utah Jazz\n" +
            "1997-98     Michael Jordan, Chicago Bulls\n" +
            "1996-97     Karl Malone, Utah Jazz\n" +
            "1995-96     Michael Jordan, Chicago Bulls\n" +
            "1994-95     David Robinson, San Antonio Spurs\n" +
            "1993-94     Hakeem Olajuwon, Houston Rockets\n" +
            "1992-93     Charles Barkley, Phoenix Suns\n" +
            "1991-92     Michael Jordan, Chicago Bulls\n" +
            "1990-91     Michael Jordan, Chicago Bulls\n" +
            "1989-90     Magic Johnson, Los Angeles Lakers\n" +
            "1988-89     Magic Johnson, Los Angeles Lakers\n" +
            "1987-88     Michael Jordan, Chicago Bulls\n" +
            "1986-87     Magic Johnson, Los Angeles Lakers\n" +
            "1985-86     Larry Bird, Boston Celtics\n" +
            "1984-85     Larry Bird, Boston Celtics\n" +
            "1983-84     Larry Bird, Boston Celtics\n" +
            "1982-83     Moses Malone, Philadelphia 76ers\n" +
            "1981-82     Moses Malone, Houston Rockets\n" +
            "1980-81     Julius Erving, Philadelphia 76ers\n" +
            "1979-80     Kareem Abdul-Jabbar, Los Angeles Lakers\n" +
            "1978-79     Moses Malone, Houston Rockets\n" +
            "1977-78     Bill Walton, Portland Trail Blazers\n" +
            "1976-77     Kareem Abdul-Jabbar, Los Angeles Lakers\n" +
            "1975-76     Kareem Abdul-Jabbar, Los Angeles Lakers\n" +
            "1974-75     Bob McAdoo, Buffalo Braves\n" +
            "1973-74     Kareem Abdul-Jabbar, Milwaukee Bucks\n" +
            "1972-73     Dave Cowens, Boston Celtics\n" +
            "1971-72     Kareem Abdul-Jabbar, Milwaukee Bucks\n" +
            "1970-71     Kareem Abdul-Jabbar, Milwaukee Bucks\n" +
            "1969-70     Willis Reed, New York Knicks\n" +
            "1968-69     Wes Unseld, Baltimore Bullets\n" +
            "1967-68     Wilt Chamberlain, Philadelphia 76ers\n" +
            "1966-67     Wilt Chamberlain, Philadelphia 76ers\n" +
            "1965-66     Wilt Chamberlain, Philadelphia 76ers\n" +
            "1964-65     Bill Russell, Boston Celtics\n" +
            "1963-64     Oscar Robertson, Cincinnati Royals\n" +
            "1962-63     Bill Russell, Boston Celtics\n" +
            "1961-62     Bill Russell, Boston Celtics\n" +
            "1960-61     Bill Russell, Boston Celtics\n" +
            "1959-60     Wilt Chamberlain, Philadelphia Warriors\n" +
            "1958-59     Bob Pettit, St. Louis Hawks\n" +
            "1957-58     Bill Russell, Boston Celtics\n" +
            "1956-57     Bob Cousy, Boston Celtics\n" +
            "1955-56     Bob Pettit, St. Louis Hawks";

    public static void main(String[] args) {
        //int max = 3;
        //List<String> alphabet = Seq.rangeClosed('A', 'Z').map(Object::toString).toList();
        //Seq.rangeClosed(1, max).flatMap(length -> Seq.rangeClosed(1, length - 1).foldLeft(Seq.seq(alphabet), (s, i) -> s.crossJoin(Seq.seq(alphabet)).map(t -> t.v1 + t.v2))).forEach(System.out::println);

        String[] lines = MVPS.split("\\n");
        Long mvpCount = Stream.of(lines).collect(teeing(
                groupingBy(e -> e.substring(12, e.indexOf(",")), summingLong(e -> 1L)),
                groupingBy(e -> e.substring(12, e.indexOf(",")), groupingBy(e -> e.substring(e.indexOf(",") + 1), counting())),
                (c, s) -> c.entrySet().stream().sorted(comparingByValue(reverseOrder())).peek(e -> out.println(e.getKey() + " " + e.getValue() + " " + s.get(e.getKey())))))
                .mapToLong(Map.Entry::getValue).sum();

        out.println(lines.length == mvpCount);
    }
}