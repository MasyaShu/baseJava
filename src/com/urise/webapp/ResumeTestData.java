package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Map<ContactType, String> contacts = new HashMap<>();
        for (ContactType c : ContactType.values()) {
            switch (c.name()) {
                case "TEL":
                    contacts.put(c, "+7(921) 855-0482");
                    break;
                case "SKYPE":
                    contacts.put(c, "grigory.kislin");
                    break;
                case "EMAIL":
                    contacts.put(c, "gkislin@yandex.ru");
                    break;
                case "LINKEDLN":
                    contacts.put(c, "https://www.linkedin.com/in/gkislin");
                    break;
                case "GITHUB":
                    contacts.put(c, "https://github.com/gkislin");
                    break;
                case "STACKOVERFLOW":
                    contacts.put(c, "https://stackoverflow.com/users/548473");
                    break;
                case "HOMEPAGE":
                    contacts.put(c, "http://gkislin.ru/");
            }
        }
        Map<SectionType, Section> sections = new HashMap<>();
        for (SectionType st : SectionType.values()) {
            if (st.name().equals("PERSONAL") || st.name().equals("OBJECTIVE")) {
                switch (st.name()) {
                    case "OBJECTIVE":
                        sections.put(st, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
                        break;
                    case "PERSONAL":
                        sections.put(st, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
                }
            } else if (st.name().equals("ACHIEVEMENT") || st.name().equals("QUALIFICATIONS")) {
                switch (st.name()) {
                    case "ACHIEVEMENT":
                        List<String> achievement = new ArrayList<>();
                        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. ");
                        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
                        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ");
                        sections.put(st, new StringListSection(achievement));
                        break;
                    case "QUALIFICATIONS":
                        List<String> qualifications = new ArrayList<>();
                        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
                        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
                        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
                        sections.put(st, new StringListSection(qualifications));
                }
            } else if (st.name().equals("EXPERIENCE") || st.name().equals("EDUCATION")) {
                switch (st.name()) {
                    case "EXPERIENCE":
                        List<EduSection> experience = new ArrayList<>();
                        experience.add(new EduSection(YearMonth.of(2013, 10), null, "Java Online Projects", "http://javaops.ru/", "Автор проекта. Создание, организация и проведение Java онлайн проектов и стажировок."));
                        experience.add(new EduSection(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Wrike", "", "Старший разработчик (backend). Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
                        experience.add(new EduSection(YearMonth.of(2012, 4), YearMonth.of(2014, 10), "RIT Center", "", "Java архитектор. Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
                        sections.put(st, new ListObjectSection(experience));
                        break;
                    case "EDUCATION":
                        List<EduSection> education = new ArrayList<>();
                        education.add(new EduSection(YearMonth.of(2013, 3), YearMonth.of(2013, 5), "Coursera", "https://www.coursera.org/course/progfun", "Functional Programming Principles in Scala\" by Martin Odersky"));
                        education.add(new EduSection(YearMonth.of(2011, 3), YearMonth.of(2011, 4), "Wrike", "", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
                        education.add(new EduSection(YearMonth.of(2005, 4), YearMonth.of(2005, 10), "Siemens AG", "", "3 месяца обучения мобильным IN сетям (Берлин)"));
                        sections.put(st, new ListObjectSection(education));
                }
            }
        }
        Resume resume = new Resume("Григорий Кислин", contacts, sections);
        printResume(resume);
    }

    static void printResume(Resume resume) {
        System.out.println(resume.getFullName());
        for (ContactType c : ContactType.values()) {
            if (resume.getContact().get(c) != null) {
                System.out.println(c.getTitle() + ": " + resume.getContact().get(c));
            }
        }

        for (SectionType st : SectionType.values()) {
            System.out.println(st.getTitle());
            Section section = resume.getSections().get(st);
            if (st.name().equals("PERSONAL") || st.name().equals("OBJECTIVE")) {
                System.out.println(section.getInfo());
            } else if (st.name().equals("ACHIEVEMENT") || st.name().equals("QUALIFICATIONS")) {
                List<String> listSection = (List<String>) section.getInfo();
                for (String sls : listSection) {
                    System.out.println(sls);
                }
            } else if (st.name().equals("EXPERIENCE") || st.name().equals("EDUCATION")) {
                List<EduSection> listSection = (List<EduSection>) section.getInfo();
                for (EduSection sls : listSection) {
                    System.out.println(sls.getEstablishment() + " (" + sls.getHttp() + ")");
                    System.out.println(sls.getData() + " - " + sls.getDataTo() + ": " + sls.getPosition());
                }
            }
        }
    }
}
