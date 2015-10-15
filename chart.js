/* 
 * Custom plugin for drawing charts using d3 js
 */

d3.SymmetryChart = function() {

    var data = 'undefined',
            margin,
            width,
            height,
            xScale = 'undefined',
            yScaleLeft = 'undefned',
            yScaleRight = 'undefned',
            xAxis = 'undefined',
            yAxisLeft = 'undefined',
            yAxisRight = 'undefined',
            xScaleFormatter = 'undefined',
            yScaleLeftFormatter = 'undefined',
            yScaleRightFormatter = 'undefined',
            xScaleProp = 'undefined',
            yScaleLeftProp = 'undefined',
            yScaleRightProp = 'undefined',
            xScaleDomain = 'undefined',
            yScaleLeftDomain = 'undefined',
            yScaleRightDomain = 'undeifned',
            yAxisLeftMarker = 'undefined',
            yAxisRightMarker = 'undefined',
            svg = 'undefined',
            selections = [];



    function draw(selection, chartName, chartConfig) {

        createSVG(selection, chartName);

        if (typeof xScale !== 'undefined' && typeof xAxis !== 'undefined') {
            createXAxis();
        }

        if (typeof yScaleLeft !== 'undefined' && typeof yAxisLeft !== 'undefined') {
            createYAxisLeft();
        }

        if (typeof yScaleRight !== 'undefined' && typeof yAxisRight !== 'undefined') {
            createYAxisRight();
        }

        for (var i = 0; i < chartConfig.length; i++) {
            
            switch (chartConfig[i].type) {
                case 'bar-chart' :
                    barChart(chartConfig[i]);
                    break;
                case 'line-chart' :
                    lineChart(chartConfig[i]);
                    break;
                case 'area-chart':
                    areaChart(chartConfig[i]);
                    break;
                default:
                    break;
            }
        }
        ;

        return draw;
    }

    function createSVG(selector, chartName) {
        svg = selector.append("svg")
                .attr("version", "1.1")
                .attr("xmlns", "http://www.w3.org/2000/svg")
                .attr("name", chartName)
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("class", "graph")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    }

    function createXAxis() {
        svg.append("g")
                .attr("class", "sy-x sy-axis")
                .attr("transform", "translate(0," + height + ")")
                .call(xAxis);
    }

    function createYAxisLeft() {
        var axisGroup = svg.append("g")
                .attr("class", "sy-y sy-axis axisLeft")
                .attr("transform", "translate(0,0)")
                .call(yAxisLeft);

        if (typeof yAxisLeftMarker !== 'undefined') {
            axisGroup.append("text")
                    .attr("y", yAxisLeftMarker.y)
                    .attr("x", yAxisLeftMarker.x)
                    .attr("dy", "-2em")
                    .style("text-anchor", "end")
                    .style("text-anchor", "end")
                    .text(yAxisLeftMarker.text);
        }

    }

    function createYAxisRight() {
        var axisGroup = svg.append("g")
                .attr("class", "sy-y sy-axis axisRight")
                .attr("transform", "translate(" + width + ",0)")
                .call(yAxisRight);

        if (typeof yAxisRightMarker !== 'undefined') {
            axisGroup.append("text")
                    .attr("y", yAxisRightMarker.y)
                    .attr("x", yAxisRightMarker.x)
                    .attr("dy", "-2em")
                    .style("text-anchor", "end")
                    .style("text-anchor", "end")
                    .text(yAxisRightMarker.text);
        }
    }

    function barChart(chartConfig) {
        var yScale, yScaleProp;
            if (chartConfig['yScale'] === 'right') {
                yScale = yScaleRight;
                yScaleProp = yScaleRightProp;
            }
            else {
                yScale = yScaleLeft;
                yScaleProp = yScaleLeftProp;
            }
            
        var bars = svg.selectAll(".sy-bar").data(data).enter();

        var barSelector = bars.append("rect")
                .attr("class", typeof chartConfig['cssClass'] !== 'undefined' ? chartConfig['cssClass'] : 'sy-bar')
                .attr("x", function(d) {
                    return xScale(d[xScaleProp]);
                })
                .attr("width", xScale.rangeBand())
                .attr("y", function(d) {
                    return yScale(d[yScaleProp]);
                })
                .attr("height", function(d, i, j) {
                    return height - yScale(d[yScaleProp]);
                });

        selections.push(barSelector);
    }

    function lineChart(chartConfig) {
        var yScale, yScaleProp;
            if (chartConfig['yScale'] === 'right') {
                yScale = yScaleRight;
                yScaleProp = yScaleRightProp;
            }
            else {
                yScale = yScaleLeft;
                yScaleProp = yScaleLeftProp;
            }
        
        var lineFunction = d3.svg.line()
                .x(function(d) {
                    return xScale(d[xScaleProp]) + xScale.rangeBand() / 2;
                })
                .y(function(d) {
                    return yScale(d[yScaleProp]);
                })
                .interpolate(chartConfig['interpolate']);

        svg.append("path").attr("d", lineFunction(data))
                .attr("class", typeof chartConfig['cssClass'] !== 'undefined' ? chartConfig['cssClass'] : 'sy-orange-line')
                .attr("stroke-width", 2)
                .attr("fill", "none");

        var lineSelector = svg.selectAll(".point")
                .data(data)
                .enter().append("circle")
                .attr("class", typeof chartConfig['cssClass'] !== 'undefined' ? chartConfig['cssClass'] : 'sy-orange-line')
                .attr("stroke-width", 2)
                .attr("fill", function(d, i) {
                    return "#fff";
                })
                .attr("cx", function(d, i) {
                    return xScale(d[xScaleProp]) + xScale.rangeBand() / 2;
                })
                .attr("cy", function(d, i) {
                    return yScale(d[yScaleProp]);
                })
                .attr("r", function(d, i) {
                    return 4;
                });

        selections.push(lineSelector);
    }


    function areaChart(chartConfig) {
        var yScale, yScaleProp;
            if (chartConfig['yScale'] === 'right') {
                yScale = yScaleRight;
                yScaleProp = yScaleRightProp;
            }
            else {
                yScale = yScaleLeft;
                yScaleProp = yScaleLeftProp;
            }
    
        var area = d3.svg.area()
                .x(function(d) {
                    return xScale(d[xScaleProp]) + xScale.rangeBand() / 2;
                })
                .y0(height)
                .y1(function(d) {
                    return yScale(d[yScaleProp]);
                })
                .interpolate(chartConfig['interpolate']);

        svg.append("path")
                .datum(data)
                .attr("class", "sy-area")
                .attr("d", area);

        var lineFunction = d3.svg.line()
                .x(function(d) {
                    return xScale(d[xScaleProp]) + xScale.rangeBand() / 2;
                })
                .y(function(d) {
                    return yScale(d[yScaleProp]);
                })
                .interpolate(chartConfig['interpolate']);

        svg.append("path").attr("d", lineFunction(data))
                .attr("class", typeof chartConfig['cssClass'] !== 'undefined' ? chartConfig['cssClass'] : 'sy-orange-line')
                .attr("stroke", "black")
                .attr("stroke-width", 2)
                .attr("fill", "none");

        var areaSelection = svg.selectAll(".point")
                .data(data)
                .enter().append("circle")
                .attr("class", typeof chartConfig['cssClass'] !== 'undefined' ? chartConfig['cssClass'] : 'sy-orange-line')
                .attr("stroke-width", 2)
                .attr("fill", function(d, i) {
                    return "#fff";
                })
                .attr("cx", function(d, i) {
                    return xScale(d[xScaleProp]) + xScale.rangeBand() / 2;
                })
                .attr("cy", function(d, i) {
                    return yScale(d[yScaleProp]);
                })
                .attr("r", function(d, i) {
                    return 4;
                });

        selections.push(areaSelection);
    }

    function init() {

        // width = width - margin.left - margin.right,
        //       height = height - margin.top - margin.bottom;
    }

    draw.setData = function(chartData) {
        if (typeof chartData !== 'undefined' && typeof chartData === 'object') {
            data = chartData;
        }

        return draw;
    };

    draw.setXScale = function(scale) {
        if (typeof scale !== 'undefined' && typeof scale === 'function') {
            xScale = scale;
        }
        else {
            xScale = d3.scale.ordinal().rangeRoundBands([0, width], .3);
        }

        return draw;
    };

    draw.getXScale = function() {
        return xScale;
    };

    draw.setYScaleLeft = function(scale) {
        if (typeof scale !== 'undefined' && typeof scale === 'function') {
            yScaleLeft = scale;
        }
        else {
            yScaleLeft = d3.scale.linear().range([height, 0]);
        }

        return draw;
    };

    draw.getYScaleLeft = function() {
        return yScaleLeft;
    };

    draw.setYScaleRight = function(scale) {
        if (typeof scale !== 'undefined' && typeof scale === 'function') {
            yScaleRight = scale;
        }
        else {
            yScaleRight = d3.scale.linear().range([height, 0]);
        }

        return draw;
    };

    draw.getYScaleRight = function() {
        return yScaleRight;
    };

    draw.setXAxis = function(axis) {
        if (typeof axis !== 'undefined' && typeof axis === 'function') {
            xAxis = axis;
        }
        else {

            xAxis = d3.svg.axis()
                    .scale(xScale)
                    .tickFormat(function(d) {
                        return xScaleFormatter(d);
                    })
                    .orient("bottom");
        }

        return draw;
    };

    draw.setYAxisLeft = function(axis) {
        if (typeof axis !== 'undefined' && typeof axis === 'function') {
            yAxisLeft = axis;
        }
        else {

            yAxisLeft = d3.svg.axis()
                    .scale(yScaleLeft)
                    .ticks(5)
                    .tickFormat(function(d) {
                        return yScaleLeftFormatter(d);
                    })
                    .orient("left");
        }

        return draw;
    };

    draw.setYAxisRight = function(axis) {
        if (typeof axis !== 'undefined' && typeof axis === 'function') {
            yAxisRight = axis;
        }
        else {

            yAxisRight = d3.svg.axis()
                    .scale(yScaleRight)
                    .ticks(5)
                    .tickFormat(function(d) {
                        return yScaleRightFormatter(d);
                    })
                    .orient("right");
        }

        return draw;
    };

    draw.setXScaleFormatter = function(formatter) {
        if (typeof formatter !== 'undefined' && typeof formatter === 'function') {
            xScaleFormatter = formatter;
        }
        else {
            xScaleFormatter = function(d) {
                return d;
            };
        }
        return draw;
    };

    draw.setYScaleLeftFormatter = function(formatter) {
        if (typeof formatter !== 'undefined' && typeof formatter === 'function') {
            yScaleLeftFormatter = formatter;
        }
        else {
            yScaleLeftFormatter = d3.format(['$', ',']);
        }

        return draw;
    };

    draw.setYScaleRightFormatter = function(formatter) {
        if (typeof formatter !== 'undefined' && typeof formatter === 'function') {
            yScaleRightFormatter = formatter;
        }
        else {
            yScaleRightFormatter = d3.format(['$', ',']);
        }

        return draw;
    };

    draw.setXScaleDomain = function(xDomain) {
        if (typeof xDomain !== 'undefined') {
            xScale.domain(xDomain);
        }
        else {
            xScale.domain(data.map(function(d) {
                return d[xScaleProp];
            }));
        }

        return draw;
    };

    draw.setYScaleLeftDomain = function(yDomain) {
        if (typeof yDomain !== 'undefined') {
            yScaleLeft.domain(yDomain);
        }
        else {
            yScaleLeft.domain([0, d3.max(data, function(d) {
                    return d[yScaleLeftProp];
                })]);
        }

        return draw;
    };

    draw.setYScaleRightDomain = function(yDomain) {
        if (typeof yDomain !== 'undefined') {
            yScaleRight.domain(yDomain);
        }
        else {
            yScaleRight.domain([0, d3.max(data, function(d) {
                    return d[yScaleRightProp];
                })]);
        }

        return draw;
    };

    draw.setXScaleProp = function(prop) {
        xScaleProp = prop;

        return draw;
    };

    draw.setYScaleLeftProp = function(prop) {
        yScaleLeftProp = prop;

        return draw;
    };

    draw.setYScaleRightProp = function(prop) {
        yScaleRightProp = prop;

        return draw;
    };

    draw.setYAxisLeftMarker = function(x, y, text) {
        yAxisLeftMarker = {x: x, y: y, text: text};

        return draw;
    };

    draw.setYAxisRightMarker = function(x, y, text) {
        yAxisRightMarker = {x: x, y: y, text: text};

        return draw;
    };

    draw.setHeight = function(h) {
        height = h;

        return draw;
    };

    draw.setWidth = function(w) {
        width = w;

        return draw;
    };

    draw.setMargin = function(m) {
        margin = m;

        return draw;
    };

    draw.getSelections = function() {
        return selections;
    };

    draw.getSvg = function() {
        return svg;
    };

    return draw;
};


