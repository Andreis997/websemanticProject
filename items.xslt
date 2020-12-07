<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Top Tools During Covid</title>
                <style>
                    body {
                    background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png);
                    font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
                    text-shadow: 0 1px 0 #fff;
                    }

                    strong {
                    font-weight: bold;
                    }

                    table {
                    border-collapse: separate;
                    box-shadow: inset 0 1px 0 #fff;
                    font-size: 12px;
                    line-height: 24px;
                    margin: 30px auto;
                    text-align: left;
                    width: 1280px;
                    }

                    th {
                    background: url(https://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
                    border-left: 1px solid #555;
                    border-right: 1px solid #777;
                    border-top: 1px solid #555;
                    border-bottom: 1px solid #333;
                    box-shadow: inset 0 1px 0 #999;
                    color: #fff;
                    font-weight: bold;
                    padding: 10px 15px;
                    position: relative;
                    text-shadow: 0 1px 0 #000;
                    }

                    th:after {
                    background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
                    content: '';
                    display: block;
                    height: 25%;
                    left: 0;
                    margin: 1px 0 0 0;
                    position: absolute;
                    top: 25%;
                    width: 100%;
                    }

                    th:first-child {
                    border-left: 1px solid #777;
                    box-shadow: inset 1px 1px 0 #999;
                    }

                    th:last-child {
                    box-shadow: inset -1px 1px 0 #999;
                    }

                    td {
                    border-right: 1px solid #fff;
                    border-left: 1px solid #e8e8e8;
                    border-top: 1px solid #fff;
                    border-bottom: 1px solid #e8e8e8;
                    padding: 2px 8px;
                    position: relative;
                    transition: all 300ms;
                    }

                    td:first-child {
                    box-shadow: inset 1px 0 0 #fff;
                    }

                    td:last-child {
                    border-right: 1px solid #e8e8e8;
                    box-shadow: inset -1px 0 0 #fff;
                    }

                    /*
                    .tr {
                    background: url(https://jackrugile.com/images/misc/noise-diagonal.png);
                    }

                    .tr:nth-child(odd) td {
                    background: #f1f1f1 url(https://jackrugile.com/images/misc/noise-diagonal.png);
                    }

                    .tr:last-of-type td {
                    box-shadow: inset 0 -1px 0 #fff;
                    }

                    .tr:last-of-type td:first-child {
                    box-shadow: inset 1px -1px 0 #fff;
                    }

                    .tr:last-of-type td:last-child {
                    box-shadow: inset -1px -1px 0 #fff;
                    }
                    */

                    tbody:hover td {
                    color: transparent;
                    text-shadow: 0 0 3px #aaa;
                    }

                    tbody:hover tr:hover td {
                    color: #444;
                    text-shadow: 0 1px 0 #fff;
                    }
                </style>
            </head>
            <body>
                <h1>Top Tools for Learning and Teaching 2020</h1>

                <table cellpadding="0" cellspacing="0" border="0">
                    <div class="tbl-header">
                        <tr bgcolor="#9acd32">
                            <th>Name</th>
                            <th>Link</th>
                            <th>Style</th>
                            <th>Category</th>
                            <th>Web Based?</th>
                            <th>Price</th>
                            <th>Subjects</th>
                            <th>Creator</th>
                            <th>Description</th>
                        </tr>
                    </div>
                    <div id="main" class="tbl-content">
                        <xsl:for-each select="tools_list/tool">
                            <xsl:if test="style = 'Learning'">
                                <tr bgcolor="lightgreen">
                                    <td>
                                        <strong>
                                            <xsl:value-of select="name"/>
                                        </strong>
                                    </td>
                                    <td>
                                        <xsl:value-of select="link"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="style"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="category"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="web_based"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="price"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="subjects"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="creator"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="description"/>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="style = 'Teaching'">
                                <tr bgcolor="lightgoldenrodyellow">
                                    <td>
                                        <strong>
                                            <xsl:value-of select="name"/>
                                        </strong>
                                    </td>
                                    <td>
                                        <xsl:value-of select="link"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="style"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="category"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="web_based"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="price"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="subjects"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="creator"/>
                                    </td>

                                    <td>
                                        <xsl:value-of select="description"/>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="style = 'Structure'">
                                <tr bgcolor="lightcyan">
                                    <td>
                                        <strong>
                                            <xsl:value-of select="name"/>
                                        </strong>
                                    </td>
                                    <td>
                                        <xsl:value-of select="link"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="style"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="category"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="web_based"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="price"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="subjects"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="creator"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="description"/>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="style = 'Joint'">
                                <tr bgcolor="linen">
                                    <td>
                                        <strong>
                                            <xsl:value-of select="name"/>
                                        </strong>
                                    </td>
                                    <td>
                                        <xsl:value-of select="link"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="style"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="category"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="web_based"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="price"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="subjects"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="creator"/>
                                    </td>
                                    <td>
                                        <xsl:value-of select="description"/>
                                    </td>
                                </tr>
                            </xsl:if>
                        </xsl:for-each>
                    </div>
                </table>
                <h2>Top Tools for Learning and Teaching 2020</h2>
                <form method="get" action="item/specific">
                    <input type="text" name="category">categorie</input>
                    <button type="submit">SUBMIT</button>
                </form>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Name</th>
                        <th>Description</th>
                        <th>Link</th>
                        <th>Style</th>
                        <th>Category</th>
                        <th>Web Based?</th>
                        <th>Price</th>
                        <th>Subjects</th>
                        <th>Creator</th>
                    </tr>
                    <xsl:for-each select="tools_list/tool">
                        <xsl:if test="style = 'Learning'">
                            <tr bgcolor="green">
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="description"/>
                                </td>
                                <td>
                                    <xsl:value-of select="link"/>
                                </td>
                                <td>
                                    <xsl:value-of select="style"/>
                                </td>
                                <td>
                                    <xsl:value-of select="category"/>
                                </td>
                                <td>
                                    <xsl:value-of select="web_based"/>
                                </td>
                                <td>
                                    <xsl:value-of select="price"/>
                                </td>
                                <td>
                                    <xsl:value-of select="subjects"/>
                                </td>
                                <td>
                                    <xsl:value-of select="creator"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="style = 'Teaching'">
                            <tr bgcolor="yellow">
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="description"/>
                                </td>
                                <td>
                                    <xsl:value-of select="link"/>
                                </td>
                                <td>
                                    <xsl:value-of select="style"/>
                                </td>
                                <td>
                                    <xsl:value-of select="category"/>
                                </td>
                                <td>
                                    <xsl:value-of select="web_based"/>
                                </td>
                                <td>
                                    <xsl:value-of select="price"/>
                                </td>
                                <td>
                                    <xsl:value-of select="subjects"/>
                                </td>
                                <td>
                                    <xsl:value-of select="creator"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="style = 'Structure'">
                            <tr bgcolor="lightblue">
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="description"/>
                                </td>
                                <td>
                                    <xsl:value-of select="link"/>
                                </td>
                                <td>
                                    <xsl:value-of select="style"/>
                                </td>
                                <td>
                                    <xsl:value-of select="category"/>
                                </td>
                                <td>
                                    <xsl:value-of select="web_based"/>
                                </td>
                                <td>
                                    <xsl:value-of select="price"/>
                                </td>
                                <td>
                                    <xsl:value-of select="subjects"/>
                                </td>
                                <td>
                                    <xsl:value-of select="creator"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="style = 'Joint'">
                            <tr bgcolor="white">
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="description"/>
                                </td>
                                <td>
                                    <xsl:value-of select="link"/>
                                </td>
                                <td>
                                    <xsl:value-of select="style"/>
                                </td>
                                <td>
                                    <xsl:value-of select="category"/>
                                </td>
                                <td>
                                    <xsl:value-of select="web_based"/>
                                </td>
                                <td>
                                    <xsl:value-of select="price"/>
                                </td>
                                <td>
                                    <xsl:value-of select="subjects"/>
                                </td>
                                <td>
                                    <xsl:value-of select="creator"/>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

