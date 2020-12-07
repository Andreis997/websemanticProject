<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Top Tools for Learning and Teaching 2020</h2>
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
                    </xsl:for-each>
                    <xsl:for-each select="tools_list/tool">
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
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

