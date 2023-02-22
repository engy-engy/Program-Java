package org.example;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;
    Storage()
    {
        quoteList = new ArrayList<>();
        quoteList.add("Начинать всегда стоит с того, что сеет сомнения. \n\nБорис Стругацкий.");
        quoteList.add("80% успеха - это появиться в нужном месте в нужное время.\n\nВуди Аллен");
        quoteList.add("Мы должны признать очевидное: понимают лишь те,кто хочет понять.\n\nБернар Вербер");
        quoteList.add("Стремитесь не к успеху, а к ценностям, которые он дает.\n\nАльберт Эйнштейн");
        quoteList.add("Надо любить жизнь больше, чем смысл жизни.\n\nФедор Достоевский");
        quoteList.add("Успех не окончателен, поражение не фатально. Лишь смелость продолжать имеет значение.\n\nКонфуций");
        quoteList.add("На самом деле жизнь проста, но мы настойчиво ее усложняем.\n\nУинстон Черчилль");
        quoteList.add("Не так важно то, что вы получите, достигнув своих целей, как то, чем вы станете, сделав это.\n\nЗиг Зиглар");
        quoteList.add("Свобода ничего не стоит, если она не включает в себя свободу ошибаться.\n\nМахатма Ганди");
        quoteList.add("Мы продукты своего прошлого, но мы не обязаны быть его заложниками.\n\nРик Уоррен");
        quoteList.add("Есть только один способ избежать критики: ничего не делайте, ничего не говорите и будьте никем.\n\nАристотель");
        quoteList.add("Вчера я был умным и поэтому хотел изменить мир. Сегодня я стал мудрым и поэтому меняю себя.\n\nДжалаладдин Руми");
        quoteList.add("Человек, которым вам суждено стать, — это только тот человек, которым вы сами решите стать.\n\nРальф Уолдо Эмерсон");
        quoteList.add("Независимо от того, через что вы проходите, в конце туннеля есть свет.\n\nДеми Ловато");
        quoteList.add("Стоит только поверить, что вы можете, — и вы уже на полпути к цели.\n\nТеодор Рузвельт");
        quoteList.add("Вы никогда не будете слишком стары, чтобы ставить другую цель или выбрать новую мечту.\n\nКлайв Стейплз Льюис");
        quoteList.add("Иногда, разбив хорошее, можно сложить что‑то лучшее.\n\nМэрилин Монро");
        quoteList.add("Когда закрывается одна дверь к счастью, тут же открывается другая. Но мы часто так долго смотрим на первую, что не замечаем вторую.\n\nЭлен Келлер");

    }

    String getRandQuote()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int)(Math.random() * quoteList.size());
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }
}
