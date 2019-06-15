<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
    <head>
        <title>WebQueue by 25_group</title>

        <style type="text/css">
            body {
                text-align: center;
            }

            table {
                margin: auto;
            }

            A:link {
                text-decoration: none;
                color: #00e;
            }
            A:visited {
                text-decoration: none;
                color: #00e;
            }
            A:active {
                text-decoration: none;
                color: #00e;
            }
            A:hover {
                color: red;
            }

            .items_table {
                border-collapse: collapse;
                border-spacing: 0;
                border-color: #ccc;
                text-align: center;
            }

            .items_table td {
                font-family: Arial, sans-serif;
                font-size: 14px;
                padding: 10px 5px;
                border-style: solid;
                border-width: 1px;
                overflow: hidden;
                word-break: normal;
                border-color: #888;
                color: #333;
                background-color: #fff;
            }

            .items_table th {
                font-family: Arial, sans-serif;
                font-size: 14px;
                font-weight: normal;
                padding: 10px 5px;
                border-style: solid;
                border-width: 1px;
                overflow: hidden;
                word-break: normal;
                border-color: #ccc;
                color: #333;
                background-color: #f0f0f0;
            }
            #hide_column {
                border: none;
                background-color: #fff;
            }
        </style>
    </head>

    <body>
        <c:if test="${fullList.size() != listAll.size()}">
            <a href="<c:url value='/redirect'/>"><b>Каталог без фильтра</b></a>
        </c:if>

        <h1>Items</h1>

        <c:if test="${!empty listItems}">
            <table class="items_table">
                <tr>
                    <th width="100">Наименование</th>
                    <th width="60">Обязательна ли для сборки</th>
                    <th width="60">Количество</th>
                    <th width="40" id="hide_column"></th>
                    <th width="40" id="hide_column"></th>
                </tr>
                <c:forEach items="${listItems}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td><c:choose>
                            <c:when test="${item.needed !=1}">Нет</c:when>
                            <c:otherwise>Да</c:otherwise>
                        </c:choose>
                        </td>
                        <td>${item.quantity}</td>
                        <td id="hide_column"><a href="<c:url value='/edit/${item.id}'/>">edit</a></td>
                        <td id="hide_column"><a href="<c:url value='/remove/${item.id}'/>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${listItems.size() < listAll.size()}">
                <table>
                    <tr>
                        <td><a href="<c:url value='/previous'/>">prev</a></td>
                        <td width="150"></td>
                        <td><a href="<c:url value='/next'/>">next</a></td>
                        <td width="90"></td>
                    </tr>
                </table>
            </c:if>

            <br/>

            <table class="items_table">
                <tr>
                    <td width="200">Можно собрать компьютеров:</td>
                    <td width="75">${countComputers}</td>
                    <th width="90" id="hide_column"></th>
                </tr>
            </table>

        </c:if>

        <br/>

        <table>
            <tr>
                <td><b>Вывести:&#160;&#160;&#160;</b></td>
                <td><a href="<c:url value='/items/selectNeeded'/>">Обязательные для сборки</a></td>
                <td width="50"></td>
                <td><a href="<c:url value='/items/selectNotNeeded'/>">Дополнительные</a></td>
            </tr>
        </table>

        <br/>

        <h2>Добавление или изменение позиций</h2>

        <c:url var="addAction" value="/items/add"/>
        <form:form action="${addAction}" commandName="item">
            <table>
                <c:if test="${!empty item.name}">
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="Наименование"/>
                        </form:label>
                    </td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="needed">
                            <spring:message text="Обязательна ли для сборки"/>
                        </form:label>
                    </td>
                    <td><form:input path="needed"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="quantity">
                            <spring:message text="Количество"/>
                        </form:label>
                    </td>
                    <td><form:input path="quantity"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="<spring:message text="Edit / Add"/>"/>
                    </td>
                </tr>
            </table>
        </form:form>

        <h1>Поиск детали</h1>

        <c:url var="searchAction" value="/items/search"/>
        <form:form action="${searchAction}" commandName="item">
            <table>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="Наименование позиции: "/>
                        </form:label>
                    </td>

                    <td>
                        <form:input path="name"/>
                    </td>

                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="<spring:message text="search"/>"/>
                    </td>
                </tr>

            </table>
        </form:form>

    </body>
</html>